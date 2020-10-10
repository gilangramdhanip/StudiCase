package com.example.studicase.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.studicase.model.StudiCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FungsiRepository(application: Application){
    private val fungsiDao : FungsiDao?
    private var studiCase : List<StudiCase>

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        fungsiDao = db!!.kelasDao()
        studiCase = fungsiDao.getStudy()
    }

    fun getStudy(): List<StudiCase> {
        return studiCase
    }

    fun insert(study: ArrayList<StudiCase>) = runBlocking {
        this.launch(Dispatchers.IO) {
            fungsiDao?.insertStudy(study)
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