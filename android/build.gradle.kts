plugins{
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.facebook.react")
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
//    id("com.android.tools.build") version "8.2.0" apply false
}

buildscript {
    extra.apply {
        set("buildToolsVersion", "34.0.0")
        set("minSdkVersion", 21)
        set("compileSdkVersion", 34)
        set("targetSdkVersion", 34)
        set("ndkVersion", "25.1.8937393")
        set("kotlinVersion", "17")

        set("authClientId", "")
    }
}
//buildscript {
//    ext {
//        buildToolsVersion = "34.0.0"
//        minSdkVersion = 21
//        compileSdkVersion = 34
//        targetSdkVersion = 34
//        ndkVersion = "25.1.8937393"
//        kotlinVersion = "1.8.0"
//    }
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath("com.android.tools.build:gradle")
//        classpath("com.facebook.react:react-native-gradle-plugin")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin")
//    }
//}
//
//apply plugin: "com.facebook.react.rootproject"
