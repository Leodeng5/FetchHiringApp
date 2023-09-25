package com.leodeng.fetchhiringapp.data

import com.leodeng.fetchhiringapp.network.ApiService

interface ItemRepositoryInterface {
    suspend fun getItems(): List<Item>
}

class ItemRepository(private val apiService: ApiService) : ItemRepositoryInterface {
    override suspend fun getItems(): List<Item> {
        return apiService.getItems()
    }
}