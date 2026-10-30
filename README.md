## About this repository

![GitHub Tag](https://img.shields.io/github/v/tag/KingsSack/Volt) ![GitHub Issues](https://img.shields.io/github/issues/KingsSack/Volt) ![GitHub Pull Requests](https://img.shields.io/github/issues-pr/KingsSack/Volt)

Volt is an open-source software library developed in Kotlin for FIRST Tech Challenge teams. It offers a new object-oriented approach to building modular and readable FTC software.

## Features

- **Built on RoadRunner Actions** for a modular and readable codebase
- **Unified Event System** to trigger Actions in Autonomous AND TeleOp OpModes
- **Modular Architecture** created with OpModes, Robots, and Attachments
- **Visual Flow Editor** for building Action flows with zero coding knowledge

## Installation

1. Open your TeamCode `[build.gradle](https://github.com/FIRST-Tech-Challenge/FtcRobotController/blob/master/TeamCode/build.gradle)`
2. In the `repositories` block, add `maven { url 'https://jitpack.io' }`
3. Add Volt as a dependency in the `dependencies` block: `implementation 'com.github.KingsSack:Volt:0.4.0'`
