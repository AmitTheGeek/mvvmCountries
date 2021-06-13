package com.mindbody.countries.ui.province

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mindbody.countries.R
import com.mindbody.countries.data.model.Country
import com.mindbody.countries.util.obtainViewModel
import com.mindbody.countries.util.replaceFragmentInActivity


class ProvinceActivity : AppCompatActivity() {

    companion object {
        const val COUNTRY = "Country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_activity_layout)
        setupViewFragment()
    }


    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.container)
            ?: ProvinceFragment.newInstance(intent.getSerializableExtra(COUNTRY) as Country).let {
                replaceFragmentInActivity(it, R.id.container)
            }
    }

    fun obtainViewModel(): ProvinceViewModel = obtainViewModel(ProvinceViewModel::class.java)
}