import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.4.30"
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
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.4.32")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt")
        // HTTP
        implementation ("io.ktor:ktor-client-core:1.5.3")
        implementation ("io.ktor:ktor-client-json:1.5.3")
        implementation ("io.ktor:ktor-client-serialization:1.5.3")
        // Date & Time
        implementation ("com.soywiz:klock-metadata:1.4.0")

    }

    sourceSets["androidMain"].dependencies {
        implementation ("com.squareup.okhttp3:logging-interceptor:4.4.1")
         // Coroutines
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

        // HTTP
        implementation ("io.ktor:ktor-client-android:1.5.3")
        implementation ("io.ktor:ktor-client-okhttp:1.5.3")


    }

    sourceSets["iosMain"].dependencies {
        // HTTP
        implementation ("io.ktor:ktor-client-ios:1.5.3")
    }

    sourceSets{
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation ("org.jetbrains.kotlin:kotlin-test-common:1.4.30")
                implementation ("org.jetbrains.kotlin:kotlin-test-annotations-common:1.4.30")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt")
                api ("io.ktor:ktor-client-mock:1.5.3")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation ("junit:junit:4.12")
                implementation ("org.jetbrains.kotlin:kotlin-test")
                implementation ("org.jetbrains.kotlin:kotlin-test-junit")

                api ("io.ktor:ktor-client-mock-jvm:1.5.3")
                api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt")

                // specific for android only
                implementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
                implementation ("org.assertj:assertj-core:3.16.1")
                implementation ("org.mockito:mockito-core:3.3.3")
                implementation ("com.android.support.test:testing-support-lib:0.1")
                implementation ("androidx.arch.core:core-testing:2.1.0")
                implementation ("org.mockito:mockito-android:2.24.5")
                implementation ("android.arch.core:core-testing:1.1.1")
                implementation ("com.google.code.gson:gson:2.8.6")
            }
        }

        val iosTest by getting {
            dependencies {
                implementation ("io.ktor:ktor-client-ios:1.5.3")
                implementation ("io.ktor:ktor-client-core-native:1.5.3")
                implementation ("io.ktor:ktor-client-json-native:1.5.3")
            }
        }

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
