package com.example.studicase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studicase.R
import com.example.studicase.model.StudiCase
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

@Database(entities = arrayOf(StudiCase::class), version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun kelasDao() : FungsiDao

    companion object{

        const val DB_NAME = "STUDY_CASE"
        private var instance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{
            if(instance == null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                }
            }
            return instance
        }

    }

}