package com.leodeng.fetchhiringapp

import com.leodeng.fetchhiringapp.data.Item
import com.leodeng.fetchhiringapp.data.ItemGroup
import com.leodeng.fetchhiringapp.utils.ItemUtils
import org.junit.Test

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertTrue

class ItemUtilsTest {

    private val itemUtilsObject = ItemUtils
    private var items: List<Item> = listOf(
        Item(1, 1, "Item 1"),
        Item(0, 2, ""),
        Item(2, 3, "Item 2"),
        Item(3, 1, "Item 3"),
        Item(4, 2, "Item 4"),
        Item(5, 3, null),
        Item(6, 1, "Item 6"),
        Item(7, 2, "Item 7"),
        Item(8, 3, "Item 8")
    )

    /*
     * Test sortItemsByName()
     */
    @Test
    fun `sortItemsByName() should return items sorted by name`() {
        // sorted by name (null, "", and then alphabetically)
        val expected = listOf(
            Item(5, 3, null),
            Item(0, 2, ""),
            Item(1, 1, "Item 1"),
            Item(2, 3, "Item 2"),
            Item(3, 1, "Item 3"),
            Item(4, 2, "Item 4"),
            Item(6, 1, "Item 6"),
            Item(7, 2, "Item 7"),
            Item(8, 3, "Item 8"),
        )
        val actual = itemUtilsObject.sortItemsByName(items)
        assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }

    /*
     * Test filterOutUnnamed()
     */
    @Test
    fun `filterOutUnnamed() should return items with null or empty names filtered out`() {
        val actual = itemUtilsObject.filterOutUnnamed(items)
        assertTrue(actual.all { item -> !item.name.isNullOrEmpty() })
    }

    /*
     * Test groupItemsByListId()
     */
    @Test
    fun `groupItemsByListId() should return items grouped by listId`() {
        val expected = listOf(
            ItemGroup(1, listOf(
                Item(1, 1, "Item 1"),
                Item(3, 1, "Item 3"),
                Item(6, 1, "Item 6")
            )),
            ItemGroup(2, listOf(
                Item(0, 2, ""),
                Item(4, 2, "Item 4"),
                Item(7, 2, "Item 7")
            )),
            ItemGroup(3, listOf(
                Item(5, 3, null),
                Item(2, 3, "Item 2"),
                Item(8, 3, "Item 8")
            ))
        )
        val actual = itemUtilsObject.groupItemsByListId(items)
        assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }
}