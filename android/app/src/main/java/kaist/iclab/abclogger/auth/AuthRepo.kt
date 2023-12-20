package kaist.iclab.abclogger.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kaist.iclab.abclogger.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepo(
    private val context: Context
) {
    private val TAG = javaClass.simpleName

    //    private var auth: FirebaseAuth = Firebase.auth
//    private fun getGoogleClient(activity: Activity): GoogleSignInClient {
//        val signInRequest = BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    .setServerClientId(BuildConfig.AUTH_CLIENT_ID)
//                    .setFilterByAuthorizedAccounts(true)
//                    .build()
//            ).build()
////        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//////            .requestIdToken(BuildConfig.AUTH_CLIENT_ID)
////            .requestEmail()
////            .build()
//        val googleClient = Identity.getSignInClient(context)
//
//        return GoogleSignIn.getClient(activity, googleSignInOption)
//    }
    fun handleSignIn(result: GetCredentialResponse) {
        // Handle the successfully returned credential.
        val credential = result.credential

        when (credential) {
            is GoogleIdTokenCredential -> {
                Log.d(TAG, credential.id)
            }
            else -> {
                // Catch any unrecognized credential type here.
                Log.e(TAG, "Unexpected type of credential")
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
                val result = credentialManager.getCredential(
                    activity,
                    request
                )
                handleSignIn(result)
                Log.d(TAG, "SUCCESS")
            } catch (e: GetCredentialException) {
                Log.e(TAG, "FAILURE $e")
            }
        }
    }


//        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestServerAuthCode("978089928839-mevpfl7i9dc0c35cl3ma701vh73iidcg.apps.googleusercontent.com")
//            .requestEmail()
//            .build()
//        val client = GoogleSignIn.getClient(activity, googleSignInOption)
//
//        return client.signInIntent
//        val signInRequest = BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
//                    .setFilterByAuthorizedAccounts(false)
//                    .build()
//            ).build()
//        val googleClient = Identity.getSignInClient(context)
//        googleClient.beginSignIn(signInRequest)
//            .addOnCompleteListener(activity) { task ->
//                if(task.isSuccessful){
//                    onSuccessListener(task.result.pendingIntent)
//                }else{
//                    Log.d(TAG, "Login Fail: ${task.exception}")
//                }
//            }
//        val account = GoogleSignIn.getLastSignedInAccount(context)
//        Log.d(TAG, account.toString())
//        val googleSignInClient = getGoogleClient(activity)
//        return googleSignInClient.signInIntent

//    }

//    fun onLoginInfo(result: ActivityResult) {
//        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//        try {
//            val account = task.getResult(ApiException::class.java)
//            Log.d(TAG, "Login Success!")
//            // 이름, 이메일 등이 필요하다면 아래와 같이 account를 통해 각 메소드를 불러올 수 있다.
//            val userName = account.givenName
//            val serverAuth = account.serverAuthCode
//            Log.d(TAG, "$userName $serverAuth")
//        } catch (e: ApiException) {
//
//            Log.e(TAG, "Login Fail: ${e}")
//        }
//    }

//    fun getUserInfo(): Flow<FirebaseUser?> = callbackFlow {
//        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
//            trySend(auth.currentUser)
//        }
//        auth.addAuthStateListener(authStateListener)
//        awaitClose {
//            auth.removeAuthStateListener(authStateListener)
//        }
//    }

    fun logout() {
//        auth.signOut()
    }
}
