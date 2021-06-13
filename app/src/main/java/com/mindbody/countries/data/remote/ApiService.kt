package com.mindbody.countries.data.remote

import com.mindbody.countries.data.model.Country
import com.mindbody.countries.data.model.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("country")
    fun getCountries(): Call<List<Country>>

    @GET("country/{id}/province")
    fun getProvinces(@Path("id") id: Int): Call<List<Province>>
}