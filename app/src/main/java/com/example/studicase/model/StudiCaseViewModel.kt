package com.example.studicase.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.studicase.database.FungsiRepository

class StudiCaseViewModel(application: Application) : AndroidViewModel(application){
    private var studyRepo = FungsiRepository(application)
    private var study : LiveData<List<StudiCase>> = studyRepo.getStudyRoom()

    fun insertStudy(stdy : StudiCase){
        studyRepo.insert(stdy)
    }

//    fun insertStudyRoom(stdy : StudiCase){
//        studyRepo.insertRoom(stdy)
//    }

    fun getStudy() : LiveData<List<StudiCase>>{
        return study
    }

    fun deleteStudy(studiCase: StudiCase){
        studyRepo.delete(studiCase)
    }

    fun updateStudy(studiCase: StudiCase){
        studyRepo.update(studiCase)
    }
}