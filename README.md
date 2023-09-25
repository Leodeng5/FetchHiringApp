Fetch Rewards Coding Exercise
=============================
A simple Android app that retrieves data from a JSON file and displays it in a list.
- Items with blank or null names are filtered out.
- Items are grouped and sorted by listId.
    - Groups are sorted by name.

Project is buildable on build tools version 33.0.1, targeting Android 13 (SDK 33).

Run
----------------
- Clone project with `git clone` or download and extract zip.
- Open project in Android Studio.
- Build and run on Android device emulator (Tested with Pixel 3a API 33).

### Run Tests
- Run unit tests with `./gradlew test`.
- Run instrumented tests with `./gradlew connectedAndroidTest`.

Architecture
----------------
### Network
ApiService.kt
- Responsible for retrieving data from the provided url.

### Model
Item.kt
- Data class that represents an item: id, listId, name.
ItemGroup.kt
- Data class that represents a group of items: listId, items.
ItemRepository.kt
- Manages retrieval of items from ApiService.

### ViewModel
ItemViewModel.kt
- Manages uiState for the ItemsScreen to observe.
ItemViewModelFactory.kt
- Handles injection of SavedStateHandle and ItemRepository into ItemViewModel.
ItemUtils.kt
- Provides helper functions for ItemViewModel to use.

### View
Android Compose Tree
- MainActivity
  - FetchAppTheme
      - ItemsScreen
          - ItemGroupListView
              - LazyColumn of ItemGroupView
                  - ItemGroupHeader
                  - Column of Item