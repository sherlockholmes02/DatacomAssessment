package com.exam.datacomassessment.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exam.datacomassessment.model.Album
import com.exam.datacomassessment.model.User
import com.exam.datacomassessment.utils.Coroutines

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var mainListener: MainInterface? = null
    val albumsLiveData = MutableLiveData<List<Album>>()
    val users: MutableList<User> = mutableListOf()

    fun getAlbums() {
        Coroutines.inputOutput {
            val albumsResponse = mainRepository.getAlbums()
            if (albumsResponse.code() == 200) {
                val albums = albumsResponse.body()
                albums?.let {
                    albums.forEachIndexed { index, album ->
                        for (user in users) {
                            if (album.userId == user.id) {
                                album.name = user.name
                                break
                            }
                        }

                        if (index == albums.size - 1) {
                            albumsLiveData.postValue(albums)
                            mainListener?.hideProgressBar()
                        }
                    }
                }
            } else {
                mainListener?.hideProgressBar()
            }
        }
    }

    fun getUsers() {
        mainListener?.showProgressBar()
        Coroutines.inputOutput {
            val userResponse = mainRepository.getUser()
            if (userResponse.code() == 200) {
                val users = userResponse.body()
                users?.let {
                    this.users.addAll(it)
                    getAlbums()
                }
            } else {
                mainListener?.hideProgressBar()
            }
        }
    }
}