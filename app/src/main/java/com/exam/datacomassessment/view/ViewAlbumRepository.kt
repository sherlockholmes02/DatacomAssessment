package com.exam.datacomassessment.view

import com.exam.datacomassessment.model.Photo
import com.exam.datacomassessment.network.ApiInterface
import retrofit2.Response

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
class ViewAlbumRepository(private val apiInterface: ApiInterface) {

    suspend fun getPhoto(albumId: Int): Response<Photo> {
        return apiInterface.getPhoto(albumId)
    }
}