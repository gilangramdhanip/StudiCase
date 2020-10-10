package com.example.studicase.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.studicase.model.StudiCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FungsiRepository(application: Application){
    private val fungsiDao : FungsiDao?
    private var studiCaseRoom : LiveData<List<StudiCase>>? = null

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        fungsiDao = db!!.kelasDao()
        studiCaseRoom = fungsiDao.getStudyFromRoom()
    }


    fun getStudyRoom(): LiveData<List<StudiCase>> {
        return studiCaseRoom!!
    }

    fun insert(study: StudiCase) = runBlocking {
        this.launch(Dispatchers.IO) {
            fungsiDao?.insertStudyRoom(study)
        }
    }

    fun insertRoom(study: StudiCase) = runBlocking {
        this.launch(Dispatchers.IO) {
            fungsiDao?.insertStudyRoom(study)
        }
    }

    fun delete(study: StudiCase) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                fungsiDao?.deleteStudy(study)
            }
        }
    }

    fun update(study: StudiCase) = runBlocking {
        this.launch(Dispatchers.IO) {
            fungsiDao?.updateStudy(study)
        }
    }

}