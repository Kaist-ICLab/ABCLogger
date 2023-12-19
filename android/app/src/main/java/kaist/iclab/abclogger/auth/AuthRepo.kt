package kaist.iclab.abclogger.auth

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kaist.iclab.abclogger.BuildConfig
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AuthRepo{
    private val TAG = javaClass.simpleName
    private var auth: FirebaseAuth = Firebase.auth
    private fun getGoogleClient(activity: Activity): GoogleSignInClient {
//        val signInRequest = BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    .setServerClientId(BuildConfig.AUTH_CLIENT_ID)
//                    .setFilterByAuthorizedAccounts(true)
//                    .build()
//            ).build()
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.AUTH_CLIENT_ID)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity, googleSignInOption)
    }


    fun loginIntent(activity: Activity): Intent? {

        if(auth.currentUser == null) {
            val googleSignInClient = getGoogleClient(activity)
            googleSignInClient.signOut()
            return googleSignInClient.signInIntent
        }else{
            return null
        }
    }
    fun onLoginInfo(result: ActivityResult){
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            Log.d(TAG, "Login Success!")
            // 이름, 이메일 등이 필요하다면 아래와 같이 account를 통해 각 메소드를 불러올 수 있다.
            val userName = account.givenName
            val serverAuth = account.serverAuthCode
            Log.d(TAG,"$userName $serverAuth")
        } catch (e: ApiException) {

            Log.e(TAG, "Login Fail: ${e}")
         }
    }

    fun getUserInfo(): Flow<FirebaseUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            auth -> trySend(auth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    fun logout() {
        auth.signOut()
    }
}
