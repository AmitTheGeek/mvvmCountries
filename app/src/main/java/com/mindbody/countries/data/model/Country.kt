package com.mindbody.countries.data.model
import com.google.gson.annotations.SerializedName
import com.mindbody.countries.data.remote.RemoteDataSource
import java.io.Serializable

data class Country constructor(
    @SerializedName("ID")
    var id: Int,
    @SerializedName("Name")
    var name: String,
    @SerializedName("PhoneCode")
    var phoneCode: String,
    @SerializedName("Code")
    var code: String
) : Serializable {
    val flagImageUrl
        get() = RemoteDataSource.FLAG_IMAGE_URL + code.toLowerCase() + ".png"
}