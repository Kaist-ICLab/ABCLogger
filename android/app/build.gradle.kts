import groovy.lang.Closure

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.facebook.react")
    id("com.google.gms.google-services")
}
react {}

//def enableProguardInReleaseBuis = false
//def jscFlavor = 'org.webkit:android-jsc:+'


android {
    ndkVersion = "25.1.8937393"
    compileSdk = 34

    namespace =  "kaist.iclab.abclogger"
    defaultConfig {
        applicationId = "kaist.iclab.abclogger"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName ="2023-12-18:001"
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
            storeFile =  file("debug.keystore")
            storePassword = "android"
            keyAlias ="androiddebugkey"
            keyPassword = "android"
        }
    }
    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
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
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")
    // Dependency for Koin Injection

}

val nativeModulesGradleFile = file("../../node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
apply(from = nativeModulesGradleFile)
val applyNativeModules = extra["applyNativeModulesAppBuildGradle"] as Closure<*>
applyNativeModules(project)