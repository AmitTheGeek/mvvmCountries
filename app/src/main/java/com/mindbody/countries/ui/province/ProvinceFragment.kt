package com.mindbody.countries.ui.province

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindbody.countries.R
import com.mindbody.countries.data.model.Country
import com.mindbody.countries.databinding.ProvinceFragmentBinding
import java.util.*


class ProvinceFragment : Fragment() {

    private lateinit var viewDataBinding: ProvinceFragmentBinding
    private lateinit var listAdapter: ProvinceAdapter

    companion object {
        private const val TAG = "CountryFragment"
        const val COUNTRY = "Country"
        fun newInstance(country: Country) = ProvinceFragment().apply {
            arguments = Bundle().apply {
                putSerializable(COUNTRY, country)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = ProvinceFragmentBinding.inflate(inflater, container, false).apply {
            provinceViewModel = (activity as ProvinceActivity).obtainViewModel()
        }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        viewDataBinding.provinceViewModel?.loadProvinces((arguments?.getSerializable(COUNTRY) as Country).id)
    }


    private fun setupListAdapter() {
        val viewModel = viewDataBinding.provinceViewModel
        if (viewModel != null) {
            listAdapter = ProvinceAdapter(ArrayList(0))
            with(viewDataBinding.recyclerView) {
                val layoutManager = LinearLayoutManager(context)
                setLayoutManager(layoutManager)
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.list_item_divider)!!)
                }
                addItemDecoration(dividerItemDecoration)
                adapter = listAdapter
            }
        } else {
            Log.w(TAG, "ViewModel not initialized when attempting to set up adapter.")
        }
    }
}