# NBA Team Viewer
Android take home assignment for theScore which consists of the following two screens

1. A list of teams with some basic team information including wins and losses count.
2. A details page which shows team details and a roster of all players.
3. The main list page contains a menu for sorting the list by wins or by losses count.

This app uses multiple android jetpack components and couples it with the ideal app architecture for a new Android application.
It tries to follow the architecture pattern as discussed in Android's official guide to app architecture.

https://developer.android.com/topic/libraries/architecture/images/final-architecture.png

- Base - Components for core system capabilities, Kotlin extensions
- Android KTX - For idiomatic concise Kotlin code.
- Architecture - A collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.
- Data Binding - Declaratively bind observable data to UI elements.
- Lifecycles - Create a UI that automatically responds to lifecycle events.
- LiveData - Build data objects that notify views when the underlying database changes.
- Navigation - Handle everything needed for in-app navigation.
- ViewModel - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
- Paging - Load and display small chunks of data at a time.
- Animations & Transitions - Move widgets and transition between screens.
- Fragment - A basic unit of composable UI.
- Layout - Lay out widgets using different algorithms.
- Material - Material Components.
- Kotlin Coroutines for managing background threads with simplified code and reducing needs for callbacks.

Third Party

- Dagger 2 A fast dependency injector.
- Retrofit 2 A configurable REST client.
- OkHttp 3 A type-safe HTTP client.
- GSON A Json - Object converter using reflection.
- Glide Image loading.
