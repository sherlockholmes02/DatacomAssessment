package com.exam.datacomassessment.network

import com.exam.datacomassessment.model.Album
import com.exam.datacomassessment.model.Photo
import com.exam.datacomassessment.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
interface ApiInterface {

    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("users")
    suspend fun getUser(
    ): Response<List<User>>

    @GET("photos/{albumId}")
    suspend fun getPhoto(
        @Path("albumId") albumId: Int
    ): Response<Photo>
}