package com.exam.datacomassessment.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
@Suppress("UNCHECKED_CAST")
class ViewAlbumViewModelFactory(private val viewAlbumRepository: ViewAlbumRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewAlbumViewModel(viewAlbumRepository) as T
    }
}