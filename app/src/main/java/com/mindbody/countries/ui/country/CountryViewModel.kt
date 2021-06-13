package com.mindbody.countries.ui.country

import android.app.Application
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.mindbody.countries.data.DataSource
import com.mindbody.countries.data.Repository
import com.mindbody.countries.data.model.Country
import com.mindbody.countries.util.SingleLiveEvent
import com.squareup.picasso.Picasso


class CountryViewModel(
    context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    companion object {
        private const val TAG = "CountryViewModel"
        @JvmStatic
        @BindingAdapter("bind:items")
        fun entries(recyclerView: RecyclerView, countries: List<Country>) =
            (recyclerView.adapter as CountryAdapter).addToList(countries)

        @JvmStatic
        @BindingAdapter(value = ["android:src", "placeHolder"], requireAll = false)
        fun setImageUrl(view: ImageView, url: String,
                        placeHolder: Int) {
            Log.d(TAG, "setImageUrl: $url")
            val requestCreator = Picasso.with(view.context).load(url)
            if (placeHolder != 0) {
                requestCreator.placeholder(placeHolder)
            }
            requestCreator.into(view)
        }
    }

    val dataLoading = ObservableBoolean(false)
    val items: ObservableList<Country> = ObservableArrayList()
    internal val openCountryDetailsEvent = SingleLiveEvent<Country>()

    fun loadCountries() {
        dataLoading.set(true)
        repository.getCountries(object : DataSource.GetCountriesCallback {
            override fun onOperationComplete(countries: List<Country>) {
                with(items) {
                    addAll(countries)
                }
                dataLoading.set(false)
            }

            override fun onOperationFailed(t: Throwable?) {
                dataLoading.set(false)
            }
        })
    }
}