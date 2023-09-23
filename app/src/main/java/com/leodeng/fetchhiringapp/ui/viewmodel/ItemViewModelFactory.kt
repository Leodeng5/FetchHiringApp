package com.leodeng.fetchhiringapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.leodeng.fetchhiringapp.data.ItemRepository

class ItemViewModelFactory(
    private val itemRepository: ItemRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(extras.createSavedStateHandle(), itemRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}