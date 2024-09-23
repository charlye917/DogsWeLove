package com.charlye934.dogstest.doglist.usecase

import com.charlye934.dogstest.core.network.BaseError
import com.charlye934.dogstest.core.network.TaskUiState
import com.charlye934.dogstest.doglist.data.repository.DogsRepository
import com.charlye934.dogstest.doglist.data.repository.model.DogsRepositoryModel
import com.charlye934.dogstest.doglist.domain.mapper.dogsListResponseToUI
import com.charlye934.dogstest.doglist.domain.usecase.GetDogsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetDogsUseCaseTest {

    private lateinit var getDogsUseCase: GetDogsUseCase
    private val repository: DogsRepository = mockk()

    @Before
    fun setUp() {
        getDogsUseCase = GetDogsUseCase(repository)
    }

    @Test
    fun `invoke should return loading state`() = runTest {
        // Given
        coEvery { repository.getAllDogsNetwork(false) } returns flow {
            emit(TaskUiState.Loading)
        }

        // When
        val result = getDogsUseCase(false).toList()

        // Then
        assertTrue(result.first() is TaskUiState.Loading)
    }

    @Test
    fun `invoke should return success state with UI data`() = runTest {
        // Given
        val dogsListResponse = listOf(
            DogsRepositoryModel(
                dogName = "Rex",
                description = "He is much more passive and is the first to suggest to rescue and not eat The Little Pilot",
                age = 5,
                image = "https://static.wikia.nocookie.net/isle-of-dogs/images/a/af/Rex.jpg/revision/latest/scale-to-width-down/666?cb=20180625001634"
            ),
            DogsRepositoryModel(
                dogName = "Spots",
                description = "Is the brother of Chief and are also a former guard dog for Mayor Kobayashi's ward",
                age = 3,
                image = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/6b/Spots.jpg/revision/latest/scale-to-width-down/666?cb=20180624191101"
            )
        )
        val expectedUiData = dogsListResponseToUI(dogsListResponse)

        coEvery { repository.getAllDogsNetwork(false) } returns flow {
            emit(TaskUiState.Loading)
            emit(TaskUiState.Success(dogsListResponse))
        }

        // When
        val result = getDogsUseCase(false).toList()

        // Then
        assertTrue(result.first() is TaskUiState.Loading)
        assertTrue(result[1] is TaskUiState.Success)
        assertEquals(expectedUiData, (result[1] as TaskUiState.Success).data)
    }

    @Test
    fun `invoke should return error state`() = runTest {
// Configura el mock para devolver un flujo que emita un error
        coEvery { repository.getAllDogsNetwork(false) } returns flow {
            emit(TaskUiState.Loading)
            emit(TaskUiState.Error(BaseError("Network error", "")))
        }
        // Ejecuta el use case
        val result = getDogsUseCase.invoke(isRefreshing = false).toList()

        assertTrue(result.first() is TaskUiState.Loading)
        assertTrue(result[1] is TaskUiState.Error)
        assertEquals("Network error", (result[1] as TaskUiState.Error).error.message)
    }
}