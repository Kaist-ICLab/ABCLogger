import groovy.lang.Closure


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
//dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//    repositories{
//        google()
//        mavenCentral()
//    }
//}

rootProject.name = "ABCLogger"

val nativeModulesGradleFile = file("../node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
apply(from = nativeModulesGradleFile)
val applyNativeModules = extra["applyNativeModulesSettingsGradle"] as Closure<*>
applyNativeModules(settings)

include(":app")
includeBuild("../node_modules/@react-native/gradle-plugin")
