package com.leodeng.fetchhiringapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.leodeng.fetchhiringapp.data.ItemRepository
import com.leodeng.fetchhiringapp.network.ApiService
import com.leodeng.fetchhiringapp.ui.theme.FetchHiringAppTheme
import com.leodeng.fetchhiringapp.ui.view.ItemsScreen
import com.leodeng.fetchhiringapp.ui.viewmodel.ItemGroupsUiState
import com.leodeng.fetchhiringapp.ui.viewmodel.ItemViewModel
import com.leodeng.fetchhiringapp.ui.viewmodel.ItemViewModelFactory

class MainActivity : ComponentActivity() {

    private val itemViewModel: ItemViewModel by viewModels(
        factoryProducer = { ItemViewModelFactory(ItemRepository(ApiService.getInstance())) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FetchHiringAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val itemsUiState: ItemGroupsUiState by itemViewModel.itemGroupsUiState.collectAsState(ItemGroupsUiState())
                    ItemsScreen(itemsUiState)
                }
            }
        }
    }
}
