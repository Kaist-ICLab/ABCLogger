import groovy.lang.Closure
import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.facebook.react")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.appdistribution")
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
        versionCode = 2
        versionName = "2024-01-03:001"
        resourceConfigurations += arrayOf("en")
        buildConfigField("String", "ANDROID_CLIENT_ID", "\"${rootProject.extra.get("androidClientId")}\"")
        buildConfigField("String", "WEB_CLIENT_ID", "\"${rootProject.extra.get("webClientId")}\"")

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = rootProject.extra.get("kotlinVersion").toString()
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
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.0")
    // Dependency for Koin Injection
    implementation("io.insert-koin:koin-android:3.5.0")
    implementation("io.insert-koin:koin-core-coroutines:3.5.0")

    implementation("androidx.credentials:credentials:1.2.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.2.0")
}

val nativeModulesGradleFile = file("../../node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
apply(from = nativeModulesGradleFile)
val applyNativeModules = extra["applyNativeModulesAppBuildGradle"] as Closure<*>
applyNativeModules(project)