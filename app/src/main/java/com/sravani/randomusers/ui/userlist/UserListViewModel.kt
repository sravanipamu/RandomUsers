package com.sravani.randomusers.ui.userlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sravani.randomusers.data.api.NetworkService
import com.sravani.randomusers.data.model.Info
import com.sravani.randomusers.data.model.RandomUserResponse
import com.sravani.randomusers.data.model.User
import com.sravani.randomusers.data.repositary.UserListRepositary
import com.sravani.randomusers.di.BaseUrl
import com.sravani.randomusers.di.ResultCount
import com.sravani.randomusers.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor( private val userListRepositary: UserListRepositary, @ResultCount val  resultCount: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<User>>> = _uiState

    init {
        fetchUserList(resultCount)
    }

     fun fetchUserList(resultCount : Int) {
         _uiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.Main) {
            userListRepositary.getRandomUsers(resultCount)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}
