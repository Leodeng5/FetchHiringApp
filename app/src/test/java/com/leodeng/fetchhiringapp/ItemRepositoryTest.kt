package com.leodeng.fetchhiringapp

import com.leodeng.fetchhiringapp.data.Item
import com.leodeng.fetchhiringapp.data.ItemRepository
import com.leodeng.fetchhiringapp.data.ItemRepositoryInterface
import com.leodeng.fetchhiringapp.network.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.openMocks

class ItemRepositoryTest {

    private lateinit var repository: ItemRepositoryInterface

    @Mock
    private lateinit var mockApiService: ApiService

    @Before
    fun setUp() {
        openMocks(this)
        repository = ItemRepository(mockApiService)
    }

    @Test
    fun `getItems() should return a list of items`() = runBlocking {
        val items = listOf(
            Item(1, 1, "Item 1"),
            Item(2, 1, "Item 2"),
            Item(3, 2, "Item 3")
        )
        Mockito.`when`(mockApiService.getItems()).thenReturn(items)
        val result = repository.getItems()
        assert(result == items)
    }

    @After
    fun tearDown() {
        openMocks(this).close()
    }
}