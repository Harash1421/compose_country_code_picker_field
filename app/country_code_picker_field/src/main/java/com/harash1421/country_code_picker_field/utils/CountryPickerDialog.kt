package com.harash1421.country_code_picker_field.utils

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.harash1421.country_code_picker_field.data.CountryCodeData
import com.harash1421.country_code_picker_field.data.getCountriesData
import com.harash1421.country_code_picker_field.data.getCountryFlag
import com.harash1421.country_code_picker_field.data.getCountryNames

@Composable
fun CountryPickerDialog(
    context: Context,
    onClick: (CountryCodeData) -> Unit,
    defSelectedCountry: CountryCodeData = getCountriesData.first(),
    showCountriesCode: Boolean = true,
    showCountriesFlag: Boolean = true,
    showCountriesName: Boolean = false
) {
    var ifOpenDialog by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val countryList: List<CountryCodeData> = getCountriesData
    var isSelectCountry by remember { mutableStateOf(defSelectedCountry) }

    Column(
        modifier = Modifier
            .padding(7.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                ifOpenDialog = true
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(showCountriesFlag){
                Image(
                    modifier = Modifier.width(34.dp),
                    painter = painterResource(id = getCountryFlag(isSelectCountry.cCode)),
                    contentDescription = "Country Flag"
                )
            }
            if(showCountriesName){
                Text(text = stringResource(id = getCountryNames(isSelectCountry.cCode.lowercase())),
                    modifier = Modifier.padding(start = 7.dp),
                    fontSize = 17.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
            }
            if(showCountriesCode){
                Text(text = isSelectCountry.countryCode,
                    modifier = Modifier.padding(start = 7.dp),
                    fontSize = 17.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
            }
        }

        if (ifOpenDialog){
            ShowDialog(
                context = context,
                countryList = countryList,
                onDismiss = { ifOpenDialog = false },
                isDialogActive = ifOpenDialog,
                onSelected = { countryItem ->
                    onClick(countryItem)
                    isSelectCountry = countryItem
                    ifOpenDialog = false
                }
            )
        }
    }
}

@Composable
fun ShowDialog(
    context: Context,
    countryList: List<CountryCodeData>,
    onDismiss: () -> Unit,
    isDialogActive: Boolean,
    onSelected: (item: CountryCodeData) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    if(!isDialogActive) searchQuery = ""

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            color = MaterialTheme.colors.onPrimary,
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchField(value = searchQuery, onValueChange = { searchQuery = it })

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn{
                    items(
                        if(searchQuery.isEmpty()){
                            countryList
                        }else{
                            countryList.filterByQuery(
                                searchQuery,
                                context
                            )
                        }
                    ){ country ->
                        CountryRow(country, onSelected)
                    }
                }
            }
        }
    }
}

@Composable
fun CountryRow(country: CountryCodeData, onClick: (CountryCodeData) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(country) }
            .padding(18.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = getCountryFlag(country.cCode)),
            contentDescription = null,
            modifier = Modifier.width(30.dp)
        )
        Text(
            text = stringResource(id = getCountryNames(country.cCode.lowercase())),
            style = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.Serif),
            modifier = Modifier.padding(horizontal = 18.dp)
        )
    }
}

fun List<CountryCodeData>.filterByQuery(query: String, context: Context) = filter {
    context.getString(getCountryNames(it.cCode)).lowercase().contains(query.lowercase())
}

