# Country Code Picker Field for Android and Jetpack Compose

compose_country_code_picker_field is a modern Android library designed to simplify the task of integrating a country selection field into your Android applications. Built with Kotlin and leveraging the power of Jetpack Compose, this library offers a seamless and intuitive experience for users to select countries.

## Screenshot:
<img src="https://github.com/Harash1421/country_picker_field/blob/master/app/src/main/res/drawable/compose_country_code_picker_field_review.png?raw=true" height="720" width="350" >


## Features:
* Jetpack Compose Integration: Native Jetpack Compose integration for an idiomatic UI experience.
* Material 3 Design: Elegant and modern UI using Material 3 design principles.
* Powered by libphonenumber: Ensures accurate and up-to-date country data.
* AndroidX Compatible: Perfect for modern Android applications.

## Requirements:
> To use the country_picker_field library efficiently, make sure to have the following setup:
* Java 17: The library is built with Java 17, so you need to have JDK 17 setup in your environment. If you haven't, you can download and install it from the [Oracle Website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* Latest Android Gradle Version: Ensure that you're using a Gradle version that is compatible with Java 17 and the Android Gradle Plugin version the library is built with.
* Latest Jetpack Compose Version: To access all features and ensure compatibility, it's recommended to use the latest version of Jetpack Compose. Check the [Jetpack Compose release notes](https://developer.android.com/jetpack/androidx/releases/compose) for the latest version and any updates.


## Installation:

> Step 1: Add JitPack repository

Add the JitPack repository to your project's root settings.gradle file:

```
dependencyResolutionManagement {
    repositories {
        ....
        maven { url 'https://jitpack.io' }
    }
}
```
</br>

> Step 2: Add the library dependency

In your module's build.gradle file, add:

```
dependencies {
    implementation 'com.github.Harash1421:country_picker_field:1.0.0'
}
```

## Usage:
> Integration:

To utilize CountryPickerField in your Composable function:


```kotlin
@ExperimentalMaterial3Api
@Composable
public fun CountryCodePickerField(
    modifier: Modifier = Modifier,
    secondStyle: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
    textFieldColors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Black,
        unfocusedBorderColor = Color.White,
        cursorColor = Color.Black
    ),
    showCountryFlag: Boolean = true,
    showCountryCode: Boolean = true,
): Unit
```
</br>

> Example:

```kotlin
var phoneNumber by remember { mutableStateOf("") }

val getPhoneNumber = remember { mutableStateOf("") }

val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
    focusedBorderColor = Color.Black,
    unfocusedBorderColor = Color.White,
    unfocusedBorderColor = Color.White,
)

CountryCodePickerField(
    modifier = Modifier.fillMaxWidth().padding(25.dp).background(Color.White),
    secondStyle = false,
    value = phoneNumber,
    onValueChange = { phoneNumber = it },
    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
    textFieldColors = textFieldColors,
    showCountryFlag = true,
    showCountryCode = true
)

Text(text = getPhoneNumber.value)

Spacer(modifier = Modifier.height(24.dp))

Button(onClick = {
  getPhoneNumber.value = fullPhoneNumber
}) {
  Text(text = "Get Phone Number")
}

```

## Contributions:

Contributions are welcome! Whether it's bug reports, feature requests, or pull requests – all are greatly appreciated. Please head over to the Issues section if you have questions or feedback.

## License:

```kotlin
Country Code Picker Field License

Copyright (c) 2023 Harash1421. All rights reserved.

Permission is hereby granted to any person obtaining a copy of this library ("compose_country_code_picker_field") and the associated documentation files, to use, copy, modify, and distribute the library, provided the following conditions are met:

1. The above copyright notice and this permission notice shall be included in all copies or substantial portions of the library.

2. The library shall not be sold or sublicensed without the explicit permission of the author, Harash1421.

3. Modifications to the library should acknowledge the original author and provide clear documentation of changes made.

THIS LIBRARY IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY CLAIM, DAMAGES, OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT, OR OTHERWISE, ARISING FROM, OUT OF, OR IN CONNECTION WITH THE LIBRARY OR THE USE OR OTHER DEALINGS IN THE LIBRARY.

```
