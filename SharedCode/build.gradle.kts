import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization") version "1.3.40"
}


kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "SharedCode"
            }
        }
    }

    jvm("android")

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        api ("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.3")
        implementation ("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.14.0")

        // HTTP
        implementation ("io.ktor:ktor-client-core:1.3.0-rc2")
        implementation ("io.ktor:ktor-client-json:1.3.0-rc2")
        implementation ("io.ktor:ktor-client-serialization:1.3.0-rc2")
        // Date & Time
        implementation ("com.soywiz:klock-metadata:1.4.0")


    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation ("com.squareup.retrofit2:converter-gson:2.6.0")
        implementation ("com.squareup.retrofit2:retrofit:2.6.0")
        implementation ("com.squareup.okhttp3:logging-interceptor:4.0.0")
         // Coroutines
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")

        implementation ("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.14.0")

        // HTTP
        implementation ("io.ktor:ktor-client-core:1.3.0-rc2")
        implementation ("io.ktor:ktor-client-android:1.3.0-rc2")
        implementation ("io.ktor:ktor-client-json-jvm:1.3.0-rc2")
        implementation ("io.ktor:ktor-client-serialization-jvm:1.3.0-rc2")
        implementation ("io.ktor:ktor-client-okhttp:1.3.0-rc2")
        implementation ("com.squareup.okhttp3:logging-interceptor:4.0.0")
        // Date & Time
    //    implementation ("com.soywiz:klock-android:1.4.0")

    }

    sourceSets["iosMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        // Coroutines
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3")

        // HTTP
        implementation ("io.ktor:ktor-client-ios:1.3.0-rc2")
        implementation ("io.ktor:ktor-client-json-native:1.3.0-rc2")
        implementation ("io.ktor:ktor-client-serialization-iosx64:1.3.0-rc2")

        // Date & Time
     //   implementation ("com.soywiz:klock-iosx64:1.5.0")


    }
}


val packForXcode by tasks.creating(Sync::class) {
    group = "build"

    //selecting the right configuration for the iOS framework depending on the Xcode environment variables
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)

    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)

    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\nexport 'JAVA_HOME=${System.getProperty("java.home")}'\ncd '${rootProject.rootDir}'\n./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)
