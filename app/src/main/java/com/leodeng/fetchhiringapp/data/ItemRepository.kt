package com.leodeng.fetchhiringapp.data

import com.leodeng.fetchhiringapp.network.ApiService

class ItemRepository (private val apiService: ApiService) {

    suspend fun getItems(): List<Item> {
        return apiService.getItems()
    }
}