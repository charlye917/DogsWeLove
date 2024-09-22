package com.charlye934.dogstest.doglist.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.charlye934.dogstest.R
import com.charlye934.dogstest.doglist.presentation.viewmodel.DogsListViewModel
import com.charlye934.dogstest.ui.components.TitleToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsListScreen(
    viewModel: DogsListViewModel
) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDogs()
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(paddingValues),
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
            ) {
                items(uiState.value.listDogs.size) { position ->
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        DogCardItem(uiState.value.listDogs[position])
                    }
                }
            }
        }
    )
}