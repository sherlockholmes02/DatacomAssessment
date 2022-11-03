package com.exam.datacomassessment.main

import com.exam.datacomassessment.model.Album
import com.exam.datacomassessment.model.User
import com.exam.datacomassessment.network.ApiInterface
import retrofit2.Response

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
class MainRepository(private val apiInterface: ApiInterface) {

    suspend fun getAlbums(): Response<List<Album>> {
        return apiInterface.getAlbums()
    }

    suspend fun getUser(userId: Int): Response<User> {
        return apiInterface.getUser(userId)
    }
}