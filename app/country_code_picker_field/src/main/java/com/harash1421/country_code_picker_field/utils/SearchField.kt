package com.harash1421.country_code_picker_field.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harash1421.country_code_picker_field.R

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    textColor: Color = Color.Black,
    hint: String = stringResource(id = R.string.search),
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            color = textColor,
            fontSize = 17.sp
        ),
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
    ){
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = Color.Black,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Box(modifier = Modifier.weight(0.5f)){
                if(value.isEmpty()) Text(text = hint,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 17.sp
                    )
                )
                it()
            }
        }
    }
}