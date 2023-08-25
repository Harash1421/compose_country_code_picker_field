package com.harash1421.country_code_picker_field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.harash1421.country_code_picker_field.data.getCountriesData
import com.harash1421.country_code_picker_field.utils.CountryPickerDialog
import com.harash1421.country_code_picker_field.utils.RealTimePhoneNumberFormatter
import com.harash1421.country_code_picker_field.utils.defaultCodeLanguage
import com.harash1421.country_code_picker_field.utils.defaultCountryCode

var fullPhoneNumber = ""
@Composable
fun CountryCodePickerField(
    modifier: Modifier = Modifier,
    secondStyle: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
    textFieldColors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Black,
        unfocusedBorderColor = Color.White,
        cursorColor = Color.Black
    ),
    showCountryFlag: Boolean = true,
    showCountryCode: Boolean = true,
){
    val context = LocalContext.current
    val keyboardController = LocalTextInputService.current

    var cCode by remember { mutableStateOf(defaultCountryCode(context)) }
    var defaultLanguage by remember { mutableStateOf(defaultCodeLanguage(context)) }

    fullPhoneNumber= "$cCode$value"

    Column(modifier = modifier) {
        if (secondStyle) {
            CountryPickerDialog(
                context = context,
                showCountriesFlag = showCountryFlag,
                showCountriesCode = showCountryCode,
                onClick = {
                    cCode = it.countryCode
                    defaultLanguage = it.cCode
                },
                defSelectedCountry = getCountriesData.single { it.cCode == defaultLanguage },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = { onValueChange(it) },
                textStyle = textStyle,
                colors = textFieldColors,
                placeholder = { Text(text = "Phone Number...") },
                singleLine = true,
                visualTransformation = RealTimePhoneNumberFormatter(getCountriesData.singleOrNull { it.cCode == defaultLanguage }?.cCode?.uppercase() ?: "+1"),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    autoCorrect = true,
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hideSoftwareKeyboard()
                }),
                leadingIcon = {
                    if (!secondStyle)
                        CountryPickerDialog(
                            context = context,
                            showCountriesFlag = showCountryFlag,
                            showCountriesCode = showCountryCode,
                            onClick = {
                                cCode = it.countryCode
                                defaultLanguage = it.cCode
                            },
                            defSelectedCountry = getCountriesData.single { it.cCode == defaultLanguage },
                        )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onValueChange("")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Clear",
                            tint = Color.Black
                        )
                    }
                }
            )
        }
    }
}
