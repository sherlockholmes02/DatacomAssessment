package com.exam.datacomassessment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exam.datacomassessment.data.daos.AlbumDao
import com.exam.datacomassessment.data.model.Album

/**
 * Created by Darryl Dave P. de Castro on 11/4/2022.
 */
@Database(
    version = 1,
    entities = [
        Album::class,
    ]
)
abstract class DatacomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private lateinit var INSTANCE: DatacomDatabase

        fun init(context: Context) {
            if (!::INSTANCE.isInitialized)
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DatacomDatabase::class.java,
                    "datacom_database"
                ).build()
        }

        fun getInstance() = INSTANCE

    }

    abstract fun albumDao(): AlbumDao
}