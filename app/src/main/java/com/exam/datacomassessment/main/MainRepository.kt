package com.exam.datacomassessment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exam.datacomassessment.data.DatacomDatabase
import com.exam.datacomassessment.data.model.Album
import com.exam.datacomassessment.data.model.User
import com.exam.datacomassessment.network.ApiInterface
import com.exam.datacomassessment.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
class MainRepository(private val apiInterface: ApiInterface, private val datacomDatabase: DatacomDatabase) {

    suspend fun getAlbums(): Response<List<Album>> {
        return apiInterface.getAlbums()
    }

    suspend fun getUser(): Response<List<User>> {
        return apiInterface.getUser()
    }

    fun saveAlbums(albums: List<Album>) {
        Coroutines.inputOutput {
            datacomDatabase.albumDao().insertAllAlbums(albums)
        }
    }

    suspend fun getAlbumsFromDb(): List<Album> {
        return withContext(Dispatchers.IO) {
            datacomDatabase.albumDao().getAlbums()
        }
    }
}