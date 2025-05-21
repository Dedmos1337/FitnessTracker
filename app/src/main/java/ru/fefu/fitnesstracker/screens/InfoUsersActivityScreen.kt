package ru.fefu.fitnesstracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.fefu.fitnesstracker.R

@Composable
fun Info_Users_Activity(navController: NavController, useractivetime: String,
                        useractivetype: String,userdistant: Double, usertime: String, usertag: String){
    var text_val by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = {navController.navigate("activuser-screen")})
            {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "стрелка",
                    modifier = Modifier.size(24.dp,24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = useractivetype,
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
        Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp)) {
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = usertag,
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 24.sp,
                color = Color(0xFF6200EE)


            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "${"%.2f".format(userdistant)} км",
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 35.sp

            )
            Text(
                text = useractivetime,
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 24.sp

            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = usertime,
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 35.sp

            )
            Text(
                text = "Старт 14:49 | Финиш 16:31",
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 24.sp

            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = text_val,
                onValueChange = {text_val = it },
                label = {Text("Комментарий")},
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily.SansSerif
                ),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color(0xFFCCCCCC)),
                modifier = Modifier.fillMaxWidth()
            )

        }



    }


}