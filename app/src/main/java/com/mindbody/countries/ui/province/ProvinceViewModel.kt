package com.mindbody.countries.ui.province

import android.app.Application
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.mindbody.countries.data.DataSource
import com.mindbody.countries.data.Repository
import com.mindbody.countries.data.model.Province

class ProvinceViewModel(
    context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    companion object {
        private const val TAG = "ProvinceViewModel"
        @JvmStatic
        @BindingAdapter("bind:items")
        fun entries(recyclerView: RecyclerView, provinces: List<Province>) =
            (recyclerView.adapter as ProvinceAdapter).addToList(provinces)
    }

    val dataLoading = ObservableBoolean(false)
    val items: ObservableList<Province> = ObservableArrayList()

    fun loadProvinces(countryId: Int) {
        dataLoading.set(true)
        repository.getProvinces(countryId,  object : DataSource.GetProvincesCallback {
            override fun onOperationComplete(provinces: List<Province>) {
                with(items) {
                    addAll(provinces)
                }
                dataLoading.set(false)
            }

            override fun onOperationFailed(t: Throwable?) {
                dataLoading.set(false)
            }
        })
    }
}