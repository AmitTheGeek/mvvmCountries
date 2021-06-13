package com.mindbody.countries.data.remote

import android.util.Log
import com.mindbody.countries.data.DataSource
import com.mindbody.countries.data.model.Country
import com.mindbody.countries.data.model.Province
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(val apiService: ApiService) : DataSource {

    companion object {

        internal const val BASE_URL = "https://connect.mindbodyonline.com/rest/worldregions/"
        const val FLAG_IMAGE_URL = "https://raw.githubusercontent.com/hampusborgos/country-flags/main/png100px/"

        private var sINSTANCE: RemoteDataSource? = null

        private const val TAG = "RemoteDataSource"


        /**
         * Returns the single instance of this class, creating it if necessary.
         */
        @JvmStatic
        fun getInstance(apiService: ApiService) =
            sINSTANCE ?: synchronized(RemoteDataSource::class.java) {
                sINSTANCE ?: RemoteDataSource(apiService)
                    .also { sINSTANCE = it }
            }


        @JvmStatic
        fun destroyInstance() {
            sINSTANCE = null
        }
    }

    override fun getCountries(callback: DataSource.GetCountriesCallback) {
        val call = apiService.getCountries()
        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                response?.run {
                    Log.d(TAG, "onResponse: $response")
                    if (isSuccessful) {
                        response.body()?.let { callback.onOperationComplete(it) }
                    } else {
                        callback.onOperationFailed()
                    }
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                callback.onOperationFailed(t);
            }
        })
    }

    override fun getProvinces(countryId: Int, callback: DataSource.GetProvincesCallback) {
        val call = apiService.getProvinces(countryId)
        call.enqueue(object : Callback<List<Province>> {
            override fun onResponse(call: Call<List<Province>>, response: Response<List<Province>>) {
                response?.run {
                    if (isSuccessful) {
                        response.body()?.let { callback.onOperationComplete(it) }
                    } else {
                        callback.onOperationFailed()
                    }
                }
            }

            override fun onFailure(call: Call<List<Province>>, t: Throwable) {
                callback.onOperationFailed(t);
            }
        })
    }
}
