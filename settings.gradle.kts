
pluginManagement {
    val kspVersion: String by settings
    val roomVersion: String by settings
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
        id("com.google.devtools.ksp") version kspVersion apply false
        id("androidx.room") version roomVersion apply false

    }
}

rootProject.name = "TravelAgency"

