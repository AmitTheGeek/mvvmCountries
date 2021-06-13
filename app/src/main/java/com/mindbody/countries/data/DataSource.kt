package com.mindbody.countries.data

import com.mindbody.countries.data.model.Country
import com.mindbody.countries.data.model.Province

interface DataSource {

    interface GetCountriesCallback {

        fun onOperationComplete(countries: List<Country>)

        fun onOperationFailed(t: Throwable? = Throwable())
    }

    fun getCountries(callback: GetCountriesCallback)

    interface GetProvincesCallback {

        fun onOperationComplete(countries: List<Province>)

        fun onOperationFailed(t: Throwable? = Throwable())
    }

    fun getProvinces(countryId : Int, callback: GetProvincesCallback)
}