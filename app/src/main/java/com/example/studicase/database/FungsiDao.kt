package com.example.studicase.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.studicase.model.StudiCase

@Dao
interface FungsiDao {
    @Query("Select * from studycase")
    fun getStudy(): List<StudiCase>

    @Query("Select * from studycase")
    fun getStudyFromRoom(): LiveData<List<StudiCase>>

    @Query("SELECT * FROM studycase  WHERE title LIKE :query")
    fun getTitle(query: String): LiveData<List<StudiCase>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudy(studyCase: ArrayList<StudiCase>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudyRoom(studyCase: StudiCase)

    @Delete
    suspend fun deleteStudy(studyCase: StudiCase)

    @Update
    suspend fun updateStudy(studyCase: StudiCase)

}