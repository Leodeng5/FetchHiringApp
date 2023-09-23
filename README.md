Instructions
----------------
Project is buildable on build tools version 33.0.1, targeting Android 13 (SDK 33).

Fetch App
----------------
A simple Android app that retrieves data from a JSON file and displays it in a list.
- Items with blank or null names are filtered out.
- Items are grouped and sorted by listId.
    - Groups are sorted by name.

Architecture
----------------
### Network
ApiService.kt
- Responsible for data retrieval from the API.
ItemRepository.kt
- Provides a clean API for the ViewModel to fetch and manage data.

### Model
Item.kt
- Data class that represents an item: id, listId, name.
ItemGroup.kt
- Data class that represents a group of items: listId, items.

### ViewModel
ItemViewModel.kt
- Manages uiState for the ItemsScreen to observe.

### View
Android Compose Tree
- MainActivity
  - FetchAppTheme
      - ItemsScreen
          - ItemGroupListView
              - LazyColumn of ItemGroupView
                  - ItemGroupHeader
                  - Column of Item