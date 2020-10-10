package com.example.studicase.service

import com.example.studicase.model.DataFromService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIInterface {

    @GET("posts")
    fun getDataFromService(): Call<Array<DataFromService>>

    object APIServiceFactory {
        fun create(): APIInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.baseURL)
                .build()

            return retrofit.create(APIInterface::class.java);
        }
    }
}