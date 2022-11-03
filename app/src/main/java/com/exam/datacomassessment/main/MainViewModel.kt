package com.exam.datacomassessment.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exam.datacomassessment.model.Album
import com.exam.datacomassessment.utils.Coroutines

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    var mainListener: MainInterface? = null
    val albumsLiveData = MutableLiveData<List<Album>>()
    val albumList: MutableList<Album> = mutableListOf()

    fun getAlbums() {
        mainListener?.showProgressBar()
        Coroutines.inputOutput {
            val albumsResponse = mainRepository.getAlbums()
            if (albumsResponse.code() == 200) {
                albumsResponse.body().let { albums ->
                    if (albums != null) {
                        albumList.addAll(albums)
                        albumList.forEachIndexed { index, album ->
                            getUserById(album, index == albumList.size - 1)
                        }
                    }
                }
            } else {
                mainListener?.hideProgressBar()
            }
        }
    }

    fun getUserById(album: Album, isLastItem: Boolean) {
        Coroutines.inputOutput {
            val userResponse = mainRepository.getUser(album.userId)
            if (userResponse.code() == 200) {
                userResponse.body().let { user ->
                    album.name = user?.name.toString()
                }
            } else {
                album.name = ""
            }

            if (isLastItem) {
                albumsLiveData.postValue(albumList)
                mainListener?.hideProgressBar()
            }
        }
    }
}