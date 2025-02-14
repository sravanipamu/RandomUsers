package com.sravani.randomusers.data.repositary

import com.sravani.randomusers.data.api.NetworkService
import com.sravani.randomusers.data.model.User
import com.sravani.randomusers.di.BaseUrl
import com.sravani.randomusers.di.ResultCount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserListRepositary @Inject constructor(private val networkService: NetworkService) {

    fun getRandomUsers(resultCount : Int): Flow<List<User>> {
        return flow {
            emit(networkService.getRandomUsers(resultCount))
        }.map {
            it.results
        }
    }

}