package com.charlye934.dogstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.charlye934.dogstest.doglist.presentation.ui.DogsListScreen
import com.charlye934.dogstest.doglist.presentation.viewmodel.DogsListViewModel
import com.charlye934.dogstest.ui.theme.DogsTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: DogsListViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            DogsTestTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    DogsListScreen(viewModel)
                }
            }
        }
    }
}