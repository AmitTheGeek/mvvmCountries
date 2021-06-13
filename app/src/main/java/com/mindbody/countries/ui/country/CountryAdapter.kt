package com.mindbody.countries.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindbody.countries.data.model.Country
import com.mindbody.countries.databinding.CountryItemBinding

class CountryAdapter(
    private var countries: MutableList<Country>,
    private val countriesViewModel: CountryViewModel
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val itemBinding = CountryItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = countries[position]
        holder?.run {
            bind(item)
            binding.root.setOnClickListener {
                countriesViewModel.openCountryDetailsEvent.value = item
            }
        }
    }

    fun addToList(countries: List<Country>) {
        val prevCount = itemCount
        this.countries.clear()
        this.countries.addAll(countries)
        if (prevCount > countries.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(prevCount, countries.size)
        }
    }

    inner class ViewHolder(val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            with(binding) {
                country = item
                executePendingBindings()
            }
        }
    }
}
