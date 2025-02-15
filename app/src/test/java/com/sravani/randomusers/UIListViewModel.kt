package com.sravani.randomusers

import com.google.common.truth.Truth.assertThat
import com.sravani.randomusers.data.model.Coordinates
import com.sravani.randomusers.data.model.Dob
import com.sravani.randomusers.data.model.Location
import com.sravani.randomusers.data.model.Name
import com.sravani.randomusers.data.model.Picture
import com.sravani.randomusers.data.model.Street
import com.sravani.randomusers.data.model.Timezone
import com.sravani.randomusers.data.model.User
import com.sravani.randomusers.data.repositary.UserListRepositary
import com.sravani.randomusers.ui.userlist.UserListViewModel
import com.sravani.randomusers.utils.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: UserListRepositary

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when repository returns users, uiState becomes Success`() = runTest {
        val sampleUsers = listOf(
            User(
                gender = "male",
                name = Name("Mr", "John", "Doe"),
                location = Location(
                    street = Street(123, "Main St"),
                    city = "Sample City",
                    state = "Sample State",
                    country = "Sample Country",
                    postcode = "12345",
                    coordinates = Coordinates("-7.4297", "-86.1175"),
                    timezone = Timezone("+9:30", "Adelaide, Darwin")
                ),
                email = "john.doe@example.com",
                picture = Picture(
                    large = "https://example.com/john_large.jpg",
                    medium = "https://example.com/john_medium.jpg",
                    thumbnail = "https://example.com/john_thumb.jpg"
                ),
                nat = "US",
                phone = "123-456-7890",
                dob = Dob("1952-08-14T22:26:03.999Z", 72)
            )
        )

        coEvery { repository.getRandomUsers(any()) } returns flow {
            emit(sampleUsers)
        }

        viewModel = UserListViewModel(repository, 5)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertThat(state).isInstanceOf(UiState.Success::class.java)
        if (state is UiState.Success) {
            assertThat(state.data).isEqualTo(sampleUsers)
        }
    }

    @Test
    fun `when repository throws error, uiState becomes Error`() = runTest {

        val errorMessage = "Test error occurred"
        coEvery { repository.getRandomUsers(any()) } returns flow {
            throw Exception(errorMessage)
        }
        viewModel = UserListViewModel(repository, 5)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertThat(state).isInstanceOf(UiState.Error::class.java)
        if (state is UiState.Error) {
            assertThat(state.message).contains(errorMessage)
        }
    }
}