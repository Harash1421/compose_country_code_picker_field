package com.harash1421.country_code_picker_field

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harash1421.country_code_picker_field.ui.theme.Country_code_picker_fieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Country_code_picker_fieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        var phoneNumber by remember { mutableStateOf("") }

                        val getPhoneNumber = remember {
                            mutableStateOf("")
                        }

                        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.Black
                        )

                        CountryCodePickerField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(25.dp)
                                .background(Color.White),
                            secondStyle = false,
                            value = phoneNumber,
                            onValueChange = {phoneNumber = it},
                            textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                            textFieldColors = textFieldColors,
                            showCountryFlag = true,
                            showCountryCode = true
                        )

                        Text(text = getPhoneNumber.value)

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(onClick = {
                            getPhoneNumber.value = fullPhoneNumber
                        }) {
                            Text(text = "Get Phone Number")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Country_code_picker_fieldTheme {
        Greeting("Android")
    }
}