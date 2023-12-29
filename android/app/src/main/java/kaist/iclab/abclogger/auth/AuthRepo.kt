package kaist.iclab.abclogger.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
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
                authStateListener = AuthStateListener() {
                    trySendBlocking(it.currentUser)
                }
            }
            auth.addAuthStateListener(authStateListener!!)
            awaitClose { 
                auth.removeAuthStateListener(authStateListener!!)
                authStateListener = null
            }
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
                    is GoogleIdTokenCredential -> {
                        Log.d(TAG, credential.id)
                        val firebaseCredential = GoogleAuthProvider.getCredential(credential.idToken, null)
                        auth.signInWithCredential(firebaseCredential)
                    }
                    else -> {
                        Log.e(TAG, "Unexpected type of credential")
                    }
                }
            } catch (e: GetCredentialException) {
                Log.e(TAG, "FAILURE $e")
            }
        }

    }

    fun logout() {
        auth.signOut()
    }
}
