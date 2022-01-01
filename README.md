# Audiogram

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![PR's Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat)](http://makeapullrequest.com)
[![GitHub followers](https://img.shields.io/github/followers/saivittalb.svg?style=social&label=Follow)](https://github.com/saivittalb?tab=followers)
[![Twitter Follow](https://img.shields.io/twitter/follow/saivittalb.svg?style=social)](https://twitter.com/saivittalb) 

An Android based audiogram profiling app that highlights the hearing loss in people. An audiogram is a graph that shows what the softest sound a test person can hear is. The test is carried out on sounds of different frequencies (i.e. pitches), thanks to which the hearing threshold for different sounds can be determined. The values in dB HL are placed on the vertical axis, the less dB will be obtained in the test, the lower the hearing loss will be. The standard in the tests is lower than 25 dB HL inclusive. The frequencies are plotted on the horizontal axis. The value of the sensitivity of our hearing is therefore at the intersection of these two values.

The application is fully functional and requires additional configuration to run locally. It requires connection to Firebase. Refer to the installation section to learn more.

Developed as a part of an project for the course EE 485 – IoT System Architecture and Design.

###### Note

Following versions were used in the development:

- Android Studio Arctic Fox: 2020.3.1 Patch 4
- Runtime Version: 11.0.10+0-b96-7281165 x86_64
- VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
- Tested on Pixel 5 API 30 using AVD Manager on Android Emulator

## Table of contents

* [License](#license)
* [Android APK build (Debug)](#android-apk-build-debug)
* [Components](#components)
* [Working](#working)
* [Installation](#installation)
* [Preview](#preview)
* [Contributing](#contributing)

## License

This project is licensed under the Apache License 2.0, a permissive license whose main conditions require preservation of copyright and license notices. Contributors provide an express grant of patent rights. Licensed works, modifications, and larger works may be distributed under different terms and without source code. Trademark use is also strictly prohibited. Any material found which vandalises or threatens any sort of plagiarism will be strictly given a legal action.

<p align="center"> Copyright (c) 2022 Sai Vittal B. All rights reserved.</p>

## Android APK Build (Debug)

The ```.apk``` debug build variant of this app that you can install on your Android device is available here in the link below. \
https://firebasestorage.googleapis.com/v0/b/audiogram-2ccc8.appspot.com/o/app-debug.apk?alt=media&token=aeaa9b31-1205-4f96-bd4a-37b747ccfc69

## Components

- Android codebase in Java
- Firebase Authentication                  (authenticate requests)
- Firestore                                (database)
- Phone/device microphone and speaker      (audiogram calibration and test)

## Working

The calibration is first done to ensure the device is calibrated as per the user's audio profile. Based on these results, a tone is played. If the user is able to listen to that tone, then they need to press a button. The result of the test is a quantified hearing loss and a graph generated on this basis.

## Installation

- Go to your Firebase console, setup this project, select Android app, add the package name of this app and download <b>google-services.json</b>.
- Move the <b>google-services.json</b> file you just downloaded into your Android app module root directory.

## Preview

<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850406-24810fc3-7895-4db1-8d98-1abc3d927046.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850407-2129d22d-5cce-4ec3-8a94-7facebf0da5c.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850409-e88955e2-5e0c-4c00-89eb-a03c654a89bc.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850410-d9b0c884-d76d-4cb3-97b3-220307bbe74d.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850411-e0760de7-9bba-4e58-a600-43f63252db92.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850412-4752436e-ee46-4d73-9c80-e87019e8d407.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850414-07edeb3e-7e09-4691-9374-dfb19c14e8fd.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850416-630b133c-7c30-4386-bc65-35fbe4336d43.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850415-00ac61d5-1917-4997-bda9-d3da17e04808.png" width="300"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/36305142/147850413-d797a3d5-b01b-4487-85f2-a90c2c6f0f72.png" width="300"></p>

## Contributing

- Fork this project by clicking the ```Fork``` button on top right corner of this page.
- Open terminal/console window.
- Clone the repository by running following command in git:
 ```bash
$ git clone https://github.com/[YOUR-USERNAME]/audiogram.git
```
- Add all changes by running this command.
```bash
$ git add .
```
- Or to add specific files only, run this command.
```bash
$ git add path/to/your/file
```
- Commit changes by running these commands.
```bash
$ git commit -m "DESCRIBE YOUR CHANGES HERE"

$ git push origin
```
- Create a Pull Request by clicking the ```New pull request``` button on your repository page.

[![ForTheBadge built-with-love](http://ForTheBadge.com/images/badges/built-with-love.svg)](https://GitHub.com/saivittalb/)
[![ForTheBadge powered-by-electricity](http://ForTheBadge.com/images/badges/powered-by-electricity.svg)](http://ForTheBadge.com)

<p align="center"> Copyright (c) 2022 Sai Vittal B. All rights reserved.</p>
<p align="center"> Made with ❤ by <a href="https://github.com/saivittalb">Sai Vittal B</a></p>