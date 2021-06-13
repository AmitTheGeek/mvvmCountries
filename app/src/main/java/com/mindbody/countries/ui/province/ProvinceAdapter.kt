package com.mindbody.countries.ui.province

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindbody.countries.data.model.Province
import com.mindbody.countries.databinding.ProvinceItemBinding

class ProvinceAdapter(
    private var provinces: MutableList<Province>) : RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val itemBinding = ProvinceItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = provinces.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = provinces[position]
        holder?.run {
            bind(item)
        }
    }

    fun addToList(provinces: List<Province>) {
        val prevCount = itemCount
        this.provinces.clear()
        this.provinces.addAll(provinces)
        if (prevCount > provinces.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(prevCount, provinces.size)
        }
    }

    inner class ViewHolder(val binding: ProvinceItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Province) {
            with(binding) {
                province = item
                executePendingBindings()
            }
        }
    }
}
