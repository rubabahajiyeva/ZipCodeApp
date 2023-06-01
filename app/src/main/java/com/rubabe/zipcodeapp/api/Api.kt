package com.rubabe.zipcodeapp.api

import com.rubabe.zipcodeapp.model.ZipCodeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("{ZIPCODE}")
    fun getZipCodeData(@Path("ZIPCODE") ZIPCODE: String): Call<ZipCodeData>
}



