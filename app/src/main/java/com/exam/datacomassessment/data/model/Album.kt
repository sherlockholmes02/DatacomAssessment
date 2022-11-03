package com.exam.datacomassessment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
@Entity(tableName = "albums")
data class Album(
    @PrimaryKey
    var id: Int,
    var userId: Int,
    var title: String,
    var name: String
)
