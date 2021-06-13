package com.mindbody.countries.ui.country

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindbody.countries.R
import com.mindbody.countries.databinding.CountryFragmentBinding
import java.util.ArrayList


class CountryFragment : Fragment() {

    private lateinit var viewDataBinding: CountryFragmentBinding
    private lateinit var listAdapter: CountryAdapter

    companion object {
        fun newInstance() = CountryFragment()
        private const val TAG = "CountryFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = CountryFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as CountryActivity).obtainViewModel()
        }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        viewDataBinding.viewmodel?.loadCountries()
    }


    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = CountryAdapter(ArrayList(0), viewModel)
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