[![official JetBrains project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# Kotlin Multiplafrorm projects: Sharing code between iOS and Android

Run in Android emulator :
Open Project with Android studio, then type below command in terminal:
./gradlew installSitDebug

Run in iOS emulator :
Open project (clean-multi-platforms/native) by Xcode
In Android studio, then type below command in terminal:
./gradlew packForXcode
In Xcode, select simulator and build.