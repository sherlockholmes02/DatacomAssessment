package com.exam.datacomassessment

import android.app.Application
import com.exam.datacomassessment.data.DatacomDatabase

/**
 * Created by Darryl Dave P. de Castro on 11/4/2022.
 */
class DatacomAssessmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DatacomDatabase.init(this)
    }
}