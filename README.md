![cleaniverseLogo](https://github.com/user-attachments/assets/5364b269-9939-409f-90d2-18733193fcc7)

# Cleaniverse

Cleaniverse is an Android application that showcases images from NASA's API. The app is built using the Clean Architecture principles to ensure a scalable, maintainable, and testable codebase.

  

## Clean Architecture

  

The project follows the Clean Architecture principles, which divides the codebase into four main modules:

  

1. **App Module**: The main module that ties everything together and serves as the entry point of the application, includes AppNavigation and DI component.

2.  **Presentation Module**: Contains the UI components and handles user interactions.

3.  **Domain Module**: Contains the business logic and use cases.

4.  **Data Module**: Handles data retrieval from various sources (e.g., network, database).

  

## Libraries and Technologies

  

The following libraries and technologies are used in this project:


-  **[Jetpack Compose](https://developer.android.com/develop/ui/compose/documentation)**: Modern toolkit for building native Android UI.

-  **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)**: Dependency injection library for Android.

-  **[Retrofit](https://github.com/square/retrofit)**: Type-safe HTTP client for Android.

-  **[Coil](https://coil-kt.github.io/coil/)**: Image loading library for Android.

-  **[Compose Navigation](https://developer.android.com/develop/ui/compose/navigation)**: Component for handling navigation within the app.

-  **[Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)**: For asynchronous code execution.

-  **[Kotlin Flows](https://developer.android.com/kotlin/flow)**: For handling asynchronous data streams.

-  **[Gson](https://github.com/google/gson)**: For JSON serialization and deserialization.

-  **[OkHttp](https://github.com/square/okhttp)**: HTTP client for network requests.

-  **[Hilt View Model](https://dagger.dev/hilt/view-model.html)**: Jetpack ViewModel that is constructor injected by Hilt

-   **[Shared Element Transitions in Compose](https://developer.android.com/develop/ui/compose/animation/shared-elements)**: For creating smooth transitions between UI elements.

  

## Getting Started

  

To get a local copy of the project up and running, follow these steps:

  

### Prerequisites

  

- Android Studio 4.2 or higher

- JDK 8 or higher

  

### Installation

  

1. Clone the repository:

```sh

git clone https://github.com/duccionarbone/cleaniverse.git
