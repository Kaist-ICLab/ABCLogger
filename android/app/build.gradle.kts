import groovy.lang.Closure
import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.facebook.react")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.appdistribution")
    id("kotlin-kapt")
}
react {}

//def enableProguardInReleaseBuis = false
//def jscFlavor = 'org.webkit:android-jsc:+'


android {
    ndkVersion = rootProject.extra.get("ndkVersion").toString()
    compileSdk = rootProject.extra.get("compileSdkVersion") as Int

    namespace =  "kaist.iclab.abclogger"
    defaultConfig {
        applicationId = "kaist.iclab.abclogger"
        minSdk = rootProject.extra.get("minSdkVersion") as Int
        targetSdk = rootProject.extra.get("targetSdkVersion") as Int
        versionCode = rootProject.extra.get("versionCode") as Int
        versionName = rootProject.extra.get("versionName").toString()
        resourceConfigurations += arrayOf("en")
        buildConfigField("String", "ANDROID_CLIENT_ID", "\"${rootProject.extra.get("androidClientId")}\"")
        buildConfigField("String", "WEB_CLIENT_ID", "\"${rootProject.extra.get("webClientId")}\"")

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures{
        buildConfig = true
    }
    signingConfigs {
        getByName("debug"){
            storeFile =  file(rootProject.extra.get("androidKeyStorePath").toString())
            storePassword = rootProject.extra.get("androidKeyStorePassword").toString()
            keyAlias = rootProject.extra.get("androidKeyAlias").toString()
            keyPassword = rootProject.extra.get("androidKeyPassword").toString()
        }
    }
    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            firebaseAppDistribution {
                artifactType = "APK"
            }
            // Caution! In production, you need to generate your own keystore file.
            // see https://reactnative.dev/docs/signed-apk-android.
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // Dependency for React-Native
    implementation("com.facebook.react:react-android")
    implementation("com.facebook.react:flipper-integration")
    implementation("com.facebook.react:hermes-android")
    // Dependency for Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-config")
    implementation("com.google.firebase:firebase-auth-ktx")
    // Auth Credential Using Google OAuth
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.0")

    // Dependency for Koin Injection
    val koin_version = "3.5.0"
    implementation("io.insert-koin:koin-android:$koin_version")
    implementation("io.insert-koin:koin-core-coroutines:$koin_version")
    // Dependency for Credential Manager
    val credential_version = "1.2.0"
    implementation("androidx.credentials:credentials:$credential_version")
    implementation("androidx.credentials:credentials-play-services-auth:$credential_version")
    // Dependency for Room DB
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version") //KSP 사용시 오류...

}

val nativeModulesGradleFile = file("../../node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
apply(from = nativeModulesGradleFile)
val applyNativeModules = extra["applyNativeModulesAppBuildGradle"] as Closure<*>
applyNativeModules(project)