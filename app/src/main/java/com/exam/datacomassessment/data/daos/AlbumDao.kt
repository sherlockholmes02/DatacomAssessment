package com.exam.datacomassessment.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exam.datacomassessment.data.model.Album

/**
 * Created by Darryl Dave P. de Castro on 11/4/2022.
 */
@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    fun getAlbums(): List<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbums(albums: List<Album>)

}