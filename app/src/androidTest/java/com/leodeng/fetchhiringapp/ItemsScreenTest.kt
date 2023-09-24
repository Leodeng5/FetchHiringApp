package com.leodeng.fetchhiringapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.leodeng.fetchhiringapp.data.Item
import com.leodeng.fetchhiringapp.data.ItemGroup
import com.leodeng.fetchhiringapp.ui.view.ItemsScreen
import com.leodeng.fetchhiringapp.ui.viewmodel.ItemGroupsUiState

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ItemsScreenTest {

    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun itemScreenTest() {
        val numOfGroups = 3
        val itemsPerGroup = 10
        testRule.setContent {
            ItemsScreen(
                uiState = ItemGroupsUiState(
                    itemGroups = getGroupList(numOfGroups, itemsPerGroup)
                )
            )
        }
        // testRule.onRoot().printToLog("TAG")
        testRule.onNodeWithText("Items").assertIsDisplayed()
        for (i in 1..numOfGroups) {
            testRule.onNodeWithText("List ID: $i").assertExists()
            for (j in 1..itemsPerGroup) {
                val itemId = j + (i - 1) * itemsPerGroup
                testRule.onNodeWithText("Item $itemId").assertExists()
            }
        }
    }

    private fun getGroupList(numOfGroups: Int, itemsPerGroup: Int): List<ItemGroup> {
        val groups = mutableListOf<ItemGroup>()
        for (i in 1..numOfGroups)
            groups.add(
                ItemGroup(
                    listId = i,
                    items = getItems(i, itemsPerGroup)
                )
            )
        return groups
    }

    private fun getItems(listId: Int, numOfItems: Int): List<Item> {
        val items = mutableListOf<Item>()
        for (i in 1..numOfItems) {
            val itemId = i + (listId - 1) * numOfItems
            items.add(
                Item(
                    id = i + (listId - 1) * numOfItems,
                    listId = listId,
                    name = "Item $itemId"
                )
            )
        }
        return items
    }
}