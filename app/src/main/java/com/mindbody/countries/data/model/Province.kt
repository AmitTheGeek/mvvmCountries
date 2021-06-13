package com.mindbody.countries.data.model
import com.google.gson.annotations.SerializedName

data class Province constructor(
    @SerializedName("ID")
    var id: Int,
    @SerializedName("Name")
    var name: String,
    @SerializedName("CountryCode")
    var countryCode: String,
    @SerializedName("Code")
    var code: String
)