package com.example.studicase.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.studicase.service.APIInterface
import com.example.studicase.service.Hasil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataServiceViewModel private constructor() {

    companion object {

        fun getData(responseCallback: (Hasil<Array<DataFromService>>) -> Unit){
            val service = APIInterface.APIServiceFactory.create()

            service.getDataFromService().enqueue(object : retrofit2.Callback<Array<DataFromService>> {
                override fun onFailure(call: Call<Array<DataFromService>>, t: Throwable) {
                    //Handle failure
                    responseCallback(Hasil.error(Exception("Gagal mengambil data!")))
                }

                override fun onResponse(call: Call<Array<DataFromService>>, response: Response<Array<DataFromService>>) {
                    val albumInfo = response.body()
                    responseCallback(Hasil.success(albumInfo))
                }

            })
        }
    }
}

//class DataServiceViewModel : AndroidViewModel(Application()) {
//
//    private val destination =  MutableLiveData<ArrayList<DataFromService>>()
//    private val apiService = APIInterface.APIServiceFactory.create()
//
//    fun setData(){
//        val dataDestination = ArrayList<DataFromService>()
//        apiService.getDataFromService().enqueue(object : Callback<Array<DataFromService>> {
//            override fun onFailure(call: Call<Array<DataFromService>>, t: Throwable) {
//                Log.d("FailureLog", t.toString())
//            }
//
//            override fun onResponse(call: Call<Array<DataFromService>>, response: Response<Array<DataFromService>>) {
//
//                if(response.isSuccessful){
//                    val dataDes = response.body()
//                    Log.d("ResponseLog", response.toString())
//                    dataDestination.addAll(dataDes)
//                    destination.postValue(dataDestination)
//                }else{
//                    Log.d("gagalresponse", response.toString())
////                    Toast.makeText(getApplication(), "Gagal", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//    }
//
//    fun getData(): LiveData<ArrayList<DataFromService>> {
//        return destination
//    }
//}