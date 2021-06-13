package com.mindbody.countries.ui.country

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mindbody.countries.R
import com.mindbody.countries.data.model.Country
import com.mindbody.countries.ui.province.ProvinceActivity
import com.mindbody.countries.util.obtainViewModel
import com.mindbody.countries.util.replaceFragmentInActivity

class CountryActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_activity_layout)
        setupViewFragment()
        viewModel = obtainViewModel().apply {
            openCountryDetailsEvent.observe(this@CountryActivity, { country ->
                openProvinceActivity(country)
            })
        }
    }

    private fun openProvinceActivity(country: Country?) {
        val intent = Intent(this, ProvinceActivity::class.java).apply {
            putExtra(ProvinceActivity.COUNTRY, country)
        }
        startActivity(intent)
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.container)
            ?: CountryFragment.newInstance().let {
                replaceFragmentInActivity(it, R.id.container)
            }
    }

    fun obtainViewModel(): CountryViewModel = obtainViewModel(CountryViewModel::class.java)
}
