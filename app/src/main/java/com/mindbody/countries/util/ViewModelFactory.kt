package com.mindbody.countries.util

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindbody.countries.data.Repository
import com.mindbody.countries.di.Injection
import com.mindbody.countries.ui.country.CountryViewModel
import com.mindbody.countries.ui.province.ProvinceViewModel


/**
 * Factory class to create View Models.
 */
class ViewModelFactory private constructor(
    private val application: Application,
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(CountryViewModel::class.java) ->
                    CountryViewModel(application, repository)
                isAssignableFrom(ProvinceViewModel::class.java) ->
                    ProvinceViewModel(application, repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(application,
                    Injection.provideTasksRepository(application.applicationContext))
                    .also { INSTANCE = it }
            }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
