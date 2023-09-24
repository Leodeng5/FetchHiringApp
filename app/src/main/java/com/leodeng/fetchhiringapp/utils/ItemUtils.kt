package com.leodeng.fetchhiringapp.utils

import com.leodeng.fetchhiringapp.data.Item
import com.leodeng.fetchhiringapp.data.ItemGroup

object ItemUtils {

    /*
     * Sort items by name
     * @param items: List of items to be sorted
     * @return List of items sorted by name
     */
    fun sortItemsByName(items: List<Item>): List<Item> {
        return items.sortedBy { item -> item.name }
    }

    /*
     * Filter out items with null or empty names
     * @param items: List of items to be filtered
     * @return List of items with null or empty names filtered out
     */
    fun filterOutUnnamed(items: List<Item>): List<Item> {
        return items.filter { item -> !item.name.isNullOrEmpty() }
    }

    /*
     * Group items by listId
     * @param items: List of items to be grouped
     * @return List of ItemGroup, each group contains items with the same listId
     */
    fun groupItemsByListId(items: List<Item>): List<ItemGroup> {
        val groupMap = items.groupBy { item -> item.listId }.toSortedMap()
        val groupList = groupMap.map { (listId, items) ->
            ItemGroup(listId, sortItemsByName(items))
        }
        return groupList
    }

}