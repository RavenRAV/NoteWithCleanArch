package com.example.cleanarchitecture.data.base

import com.example.cleanarchitecture.data.mappers.toNoteEntity
import com.example.cleanarchitecture.domain.model.Note
import com.example.cleanarchitecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            val data = request()
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: " Unknown exception"))
        }
    }.flowOn(Dispatchers.IO)

}