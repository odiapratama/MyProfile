# MyProfile App

<p align="left">
<img src="/assets/myprofile.gif" width="32%"/>
</p>

## Overview

The MyProfile App is an Android application built using Kotlin and Jetpack Compose. It provides a user interface to display a list of users and their details. The app uses various modern Android development libraries and follows best practices for architecture and design.

## Features

- Display a list of users
- View detailed information about a selected user
- Handle different states (loading, error, empty) gracefully
- Format dates consistently across different Android SDK versions

## Technologies Used

- **Kotlin**: The primary programming language used for the app.
- **Jetpack Compose**: For building the UI.
- **Hilt**: For dependency injection.
- **Retrofit**: For network requests.
- **Room**: For local database storage.
- **Coil**: For image loading.
- **Coroutines**: For asynchronous programming.

## Project Structure

- `app`: Contains the main code for the app.
- `core`: Contains core utilities and data models.
    - `data`: Data models and repository interfaces.
      - `local`: Local data sources (e.g., Room database).
      - `model`: Data models used throughout the app.
      - `remote`: Remote data sources (e.g., Retrofit API).
    - `ui`: Base classes for UI components.
    - `utils`: Utility functions and constants.
- `feature`: Contains feature-specific code.
    - `profile`: Code related to the user profile feature.
      - `di`: Dependency injection modules.
      - `domain`: Use cases and domain models.
      - `repository`: Repository implementation.
      - `ui`: Composable UI components.
- `gradle`: Configuration files for project dependencies and plugins.

## Dependencies

The project uses the following dependencies, managed via Gradle:

- AndroidX Core KTX
- JUnit
- Espresso
- Lifecycle Runtime KTX
- Activity Compose
- Jetpack Compose
- Coil
- Hilt
- Kotlin Coroutines
- Kotlin Serialization
- Retrofit
- Moshi
- OkHttp
- Room
- Paging

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync the project with Gradle.
4. Run the app on an emulator or physical device.

## License

This project is licensed under the MIT License.