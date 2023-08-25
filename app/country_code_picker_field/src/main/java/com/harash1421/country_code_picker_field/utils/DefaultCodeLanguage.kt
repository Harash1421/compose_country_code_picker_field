package com.harash1421.country_code_picker_field.utils

import android.content.Context
import android.telephony.TelephonyManager
import androidx.compose.ui.text.intl.Locale

fun defaultCodeLanguage(context: Context): String{
    val codeLocale: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val countryCode = codeLocale.networkCountryIso
    val localeDefault = Locale.current.language
    return countryCode.ifBlank { localeDefault }
}