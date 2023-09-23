package com.leodeng.fetchhiringapp.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leodeng.fetchhiringapp.data.Item
import com.leodeng.fetchhiringapp.data.ItemGroup
import com.leodeng.fetchhiringapp.ui.viewmodel.ItemGroupsUiState

@Composable
fun ItemsScreen(uiState: ItemGroupsUiState) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Text(
            "Items",
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            textAlign = TextAlign.Center,
        )
        ItemGroupListView(uiState.itemGroups)
    }
}

@Composable
fun ItemGroupListView(groups: List<ItemGroup>) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        )
        {
            Text(text = "ID", color = Color.White)
            Text(text = "Name", color = Color.White)
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(groups) { group ->
                ItemGroupView(group)
            }
        }
    }
}

@Composable
fun ItemGroupView(group: ItemGroup) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column (
            Modifier
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "List ID: ${group.listId}",
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline,
            )
            for (item in group.items) {
                Item(item)
            }
        }
    }
}

@Composable
fun Item(item: Item) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Text(text = "${item.id}")
        Text(text = "${item.name}")
    }
}