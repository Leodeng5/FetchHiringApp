package com.leodeng.fetchhiringapp.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leodeng.fetchhiringapp.data.Item
import com.leodeng.fetchhiringapp.data.ItemGroup
import com.leodeng.fetchhiringapp.data.ItemRepositoryInterface
import com.leodeng.fetchhiringapp.utils.ItemUtils
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ItemViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val itemRepository: ItemRepositoryInterface
) : ViewModel() {

    private val loading: StateFlow<Boolean> = savedStateHandle.getStateFlow("loading", false)
    private val error: StateFlow<Boolean> = savedStateHandle.getStateFlow("error", false)
    private val items = savedStateHandle.getStateFlow("items", emptyList<Item>())
    // OPTIMIZATION: memoize namedItems and itemGroups to avoid unnecessary recomputing
    private val namedItems = items.map { ItemUtils.filterOutUnnamed(it) }
    private val itemGroups = namedItems.map { ItemUtils.groupItemsByListId(it) }

    val itemGroupsUiState = combine(loading, error, itemGroups) { loading, error, groups ->
        ItemGroupsUiState(loading = loading, error = error, itemGroups = groups)
    }

    init {
        getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            try {
                setLoading(true)
                val items = itemRepository.getItems()
                setItems(items)
                setError(false)
                setLoading(false)
            } catch (e: Exception) {
                setError(true)
                setLoading(false)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        savedStateHandle["loading"] = loading
    }
    private fun setError(error: Boolean) {
        savedStateHandle["error"] = error
    }
    private fun setItems(items: List<Item>) {
        savedStateHandle["items"] = items
    }
}

data class ItemGroupsUiState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val itemGroups: List<ItemGroup> = emptyList(),
)