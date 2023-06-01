package com.rubabe.zipcodeapp

import com.rubabe.zipcodeapp.api.Api
import com.rubabe.zipcodeapp.network.RetrofitClient

class Constants {
    companion object{
        val BASE_URL = "https://ziptasticapi.com/"

        fun getApi(): Api {
            return RetrofitClient.getClient(BASE_URL).create(Api::class.java)
        }
    }
}