package com.mindbody.countries.di

import android.content.Context
import com.mindbody.countries.data.Repository
import com.mindbody.countries.data.remote.ApiService
import com.mindbody.countries.data.remote.RemoteDataSource
import com.mindbody.countries.data.remote.RemoteDataSource.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    fun provideTasksRepository(context: Context): Repository {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        return Repository.getInstance(RemoteDataSource.getInstance(apiService))
    }
}
