package com.harash1421.country_code_picker_field.utils

import android.content.Context
import com.harash1421.country_code_picker_field.data.getCountriesData

fun defaultCountryCode(context: Context): String{
    val defCountry = defaultCodeLanguage(context)
    val defCode = getCountriesData.firstOrNull { it.cCode == defCountry }
    return defCode?.countryCode ?: "+1"
}