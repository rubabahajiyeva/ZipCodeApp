package com.rubabe.zipcodeapp.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rubabe.zipcodeapp.Constants
import com.rubabe.zipcodeapp.api.Api
import com.rubabe.zipcodeapp.model.ZipCodeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class State{
    SUCCESS, ERROR
}

class MainActivityViewModel:ViewModel(){
    lateinit var api: Api
    var zipCodeLiveData = MutableLiveData<ZipCodeData>()
    var state = MutableLiveData<State>()
    fun getZipCode(context : Context, zipcode: String) {
        api = Constants.getApi()
        api.getZipCodeData(zipcode).enqueue(object: Callback<ZipCodeData> {
            override fun onResponse(call: Call<ZipCodeData>, response: Response<ZipCodeData>) {
                val data: ZipCodeData? = response.body()
                this@MainActivityViewModel.zipCodeLiveData.postValue(data)
                if(data?.country != null && data?.state != null && data?.city != null){
                    this@MainActivityViewModel.state.postValue(State.SUCCESS)
                }else{
                    this@MainActivityViewModel.state.postValue(State.ERROR)
                }
            }

            override fun onFailure(call: Call<ZipCodeData>, t: Throwable) {
                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG).show()
            }


        })

    }

}