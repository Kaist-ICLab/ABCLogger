import groovy.lang.Closure

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.facebook.react")
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
    // The version of react-native is set by the React Native Gradle Plugin
    implementation("com.facebook.react:react-android")
    implementation("com.facebook.react:flipper-integration")
    implementation("com.facebook.react:hermes-android")
//    if (hermesEnabled.toBoolean()) {
//        implementation("com.facebook.react:hermes-android")
//    } else {
//        implementation jscFlavor
//    }
}

val nativeModulesGradleFile = file("../../node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
apply(from = nativeModulesGradleFile)
val applyNativeModules = extra["applyNativeModulesAppBuildGradle"] as Closure<*>
applyNativeModules(project)