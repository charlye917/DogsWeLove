package com.charlye934.dogstest.doglist.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.charlye934.dogstest.R
import com.charlye934.dogstest.doglist.presentation.viewmodel.DogsListViewModel
import com.charlye934.dogstest.ui.components.PullToRefreshLazyColumn
import com.charlye934.dogstest.ui.components.TitleToolbar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsListScreen(
    viewModel: DogsListViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getDogs(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TitleToolbar(stringResource(R.string.title_app))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                PullToRefreshLazyColumn(
                    items = uiState.value.listDogs,
                    content = { itemData ->
                        if (uiState.value.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        } else {
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary)
                            ) {
                                DogCardItem(itemData)
                            }
                        }
                    },
                    isRefreshing = uiState.value.isLoading,
                    onRefresh = {
                        scope.launch {
                            viewModel.getDogs(true)
                        }
                    }
                )
            }
        }
    )
}