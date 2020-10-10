package com.example.studicase.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "studycase")
data class StudiCase(
    @ColumnInfo(name ="userId")
    var userId : String? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    var id : Int? = null,
    @ColumnInfo(name ="title")
    var title : String? = null,
    @ColumnInfo(name ="body")
    var body : String? = null
) : Parcelable