package com.leodeng.fetchhiringapp

import androidx.lifecycle.SavedStateHandle
import com.leodeng.fetchhiringapp.data.Item
import com.leodeng.fetchhiringapp.data.ItemGroup
import com.leodeng.fetchhiringapp.data.ItemRepositoryInterface
import com.leodeng.fetchhiringapp.ui.viewmodel.ItemGroupsUiState
import com.leodeng.fetchhiringapp.ui.viewmodel.ItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.openMocks

@ExperimentalCoroutinesApi
class MainDispatcherRule(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class ItemViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var itemViewModel: ItemViewModel

    @Mock
    private lateinit var mockItemRepository: ItemRepositoryInterface

    @Before
    fun setUp() {
        openMocks(this)
        val savedState = SavedStateHandle(mapOf(
            "loading" to false,
            "error" to false,
            "items" to emptyList<Item>()
        ))
        itemViewModel = ItemViewModel(savedState, mockItemRepository)
    }

    @Test
    fun `getItems() should update loading, error, and items`() = runTest {
        val mockItems = listOf(
            Item(1, 1, "Item 1"),
            Item(2, 1, "Item 2"),
            Item(3, 2, "Item 3"),
            Item(4, 2, "Item 4"),
            Item(5, 3, "Item 5"),
            Item(6, 3, ""),
            Item(7, 1, null),
        )

        Mockito.`when`(mockItemRepository.getItems()).thenReturn(mockItems)
        itemViewModel.getItems()

        val uiStates = mutableListOf<ItemGroupsUiState>()
        val job = launch(
            Dispatchers.Main
        ) {
            itemViewModel.itemGroupsUiState.collect { uiStates.add(it) }
        }

        val expectedState = ItemGroupsUiState(loading = false, error = false, itemGroups = listOf(
            ItemGroup(1, listOf(
                Item(1, 1, "Item 1"),
                Item(2, 1, "Item 2"),
            )),
            ItemGroup(2, listOf(
                Item(3, 2, "Item 3"),
                Item(4, 2, "Item 4"),
            )),
            ItemGroup(3, listOf(
                Item(5, 3, "Item 5"),
            )),
        ))

        job.cancel()

        assertEquals(uiStates[0].loading, expectedState.loading)
        assertEquals(uiStates[0].error, expectedState.error)
        assertArrayEquals(
            uiStates[0].itemGroups.toTypedArray(),
            expectedState.itemGroups.toTypedArray()
        )
    }
}