package com.harash1421.country_code_picker_field.data

import com.harash1421.country_code_picker_field.R
import java.util.Locale

data class CountryCodeData(
    var countryNameCodes: String,
    var countryCode: String = "+1",
    val countryName: String = "us",
    val countryFlagId: Int = R.drawable.us
){
    val cCode = countryNameCodes.lowercase(Locale.getDefault())
}