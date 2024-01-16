package kaist.iclab.abclogger.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kaist.iclab.abclogger.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch


class AuthRepo(
    private val context: Context
) {
    private val TAG = javaClass.simpleName

    private var auth: FirebaseAuth = Firebase.auth
    private var authStateListener: AuthStateListener? = null
    fun currentUserFlow(): Flow<FirebaseUser?> {
        return callbackFlow {
            if(authStateListener ==  null){
                authStateListener = object: AuthStateListener {
                    override fun onAuthStateChanged(auth: FirebaseAuth) {
                        Log.d(TAG, "onAuthStateChanged")
                        trySend(auth.currentUser)
                    }
                }
                auth.addAuthStateListener(authStateListener!!)
            }
            awaitClose {
                authStateListener?.let{ listener ->
                    auth.removeAuthStateListener(listener)
                    authStateListener = null
                    Log.d(TAG, "AuthStateListener Removed")
                }
            }
            Log.d(TAG, "AuthStateListener Removed2")
        }
    }

    fun login(activity: Activity) {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.WEB_CLIENT_ID)
            .setAutoSelectEnabled(true)
            .build()

        val credentialManager = CredentialManager.create(context)
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val credential = credentialManager.getCredential(
                    activity,
                    request
                ).credential
                when (credential) {
                    is CustomCredential -> {
                        if(credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                            val googleCredential = GoogleIdTokenCredential.createFrom(credential.data)
                            Log.d(TAG, googleCredential.id)
                            val firebaseCredential = GoogleAuthProvider.getCredential(
                                googleCredential.idToken, null)
                            Firebase.crashlytics.setUserId(googleCredential.id)
                            auth.signInWithCredential(firebaseCredential)
                        }else{
                            Log.e(TAG, "Unexpected type of credential ${credential.type}")
                        }
                    }
                    else -> {
                        Log.e(TAG, "Unexpected type of credential ${credential.type}")
                    }
                }
            } catch (e: GetCredentialException) {
                Log.e(TAG, "FAILURE $e")
            }
        }

    }

    fun logout() {
        Log.d(TAG, "${authStateListener == null}")
        Log.d(TAG, "logout")
        Firebase.crashlytics.setUserId(null.toString())
        auth.signOut()
    }
}
