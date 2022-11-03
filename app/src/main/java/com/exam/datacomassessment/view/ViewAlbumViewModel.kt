package com.exam.datacomassessment.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exam.datacomassessment.model.Photo
import com.exam.datacomassessment.utils.Coroutines

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
class ViewAlbumViewModel(private val viewAlbumRepository: ViewAlbumRepository) : ViewModel() {

    var viewAlbumListener: ViewAlbumInterface? = null
    val photoLiveData = MutableLiveData<Photo>()

    fun getPhoto(albumId: Int) {
        viewAlbumListener?.showProgressBar()
        Coroutines.inputOutput {
            val photoResponse = viewAlbumRepository.getPhoto(albumId)
            if (photoResponse.code() == 200) {
                photoResponse.body()?.let { photo ->
                    viewAlbumListener?.hideProgressBar()
                    photoLiveData.postValue(photo)
                }
            } else {
                viewAlbumListener?.hideProgressBar()
            }
        }
    }
}