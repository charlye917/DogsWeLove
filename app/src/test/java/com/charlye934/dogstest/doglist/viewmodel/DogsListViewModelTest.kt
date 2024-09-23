package com.charlye934.dogstest.doglist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.charlye934.dogstest.core.network.BaseError
import com.charlye934.dogstest.core.network.TaskUiState
import com.charlye934.dogstest.doglist.domain.model.DogsUI
import com.charlye934.dogstest.doglist.domain.usecase.GetDogsUseCase
import com.charlye934.dogstest.doglist.presentation.viewmodel.DogsListViewModel
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogsListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DogsListViewModel
    private val getDogsUseCase: GetDogsUseCase = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = DogsListViewModel(getDogsUseCase)
    }

    @Test
    fun `test getDogs returns loading state`() = runTest {
        // Given
        coEvery { getDogsUseCase(true) } returns flow {
            emit(TaskUiState.Loading)
        }

        // When
        viewModel.getDogs(true)

        // Then
        assertEquals(true, viewModel.uiState.value.isLoading)
    }

    @Test
    fun `test getDogs returns success state`() = runTest {
        // Given
        val dogsList = listOf(
            DogsUI(
                dogName = "Rex",
                dogDescription = "He is much more passive and is the first to suggest to rescue and not eat The Little Pilot",
                age = "5",
                image = "https://static.wikia.nocookie.net/isle-of-dogs/images/a/af/Rex.jpg/revision/latest/scale-to-width-down/666?cb=20180625001634"
            ),
            DogsUI(
                dogName = "Spots",
                dogDescription = "Is the brother of Chief and are also a former guard dog for Mayor Kobayashi's ward",
                age = "3",
                image = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/6b/Spots.jpg/revision/latest/scale-to-width-down/666?cb=20180624191101"
            )
        )

        coEvery { getDogsUseCase(true) } returns flow {
            emit(TaskUiState.Success(dogsList))
        }

        // When
        viewModel.getDogs(true)

        // Then
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(false, viewModel.uiState.value.isError)
        assertEquals(dogsList, viewModel.uiState.value.listDogs)
    }

    @Test
    fun `test getDogs returns error state`() = runTest {
        // Given
        coEvery { getDogsUseCase(true) } returns flow {
            emit(TaskUiState.Error(BaseError(message = "Generic error", code = "400")))
        }

        // When
        viewModel.getDogs(true)

        // Then
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(true, viewModel.uiState.value.isError)
        assertEquals(listOf<DogsUI>(), viewModel.uiState.value.listDogs)
    }

    @After
    fun tearDown() {
        clearMocks(getDogsUseCase)
    }
}