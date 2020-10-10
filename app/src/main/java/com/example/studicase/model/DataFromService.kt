package com.example.studicase.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataFromService (
    val userId : String,
    val id : Int,
    val title : String,
    val body : String
) : Parcelable