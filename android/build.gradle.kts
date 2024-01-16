plugins{
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
    id("com.google.firebase.appdistribution") version "4.0.0" apply false
    id("com.facebook.react")
}

buildscript {
    extra.apply {
        set("buildToolsVersion", "34.0.0")
        set("minSdkVersion", 21)
        set("compileSdkVersion", 34)
        set("targetSdkVersion", 34)
        set("ndkVersion", "25.1.8937393")
        set("kotlinVersion", "1.8.21")
        // Insert key-value pairs into rootProject.extra
        val secretObject = groovy.json.JsonSlurper().parseText(file("../secrets.json").readText()) as Map<String, Any>
        secretObject.forEach {
                (key,value) -> set(key,value)
        }
        val versionObject = groovy.json.JsonSlurper().parseText(file("app/version.json").readText()) as Map<String, Any>
        versionObject.forEach {
                (key,value) -> set(key,value)
        }
    }
}
