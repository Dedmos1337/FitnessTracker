package ru.fefu.fitnesstracker.screens

import android.os.Bundle
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.items
import androidx.annotation.Discouraged
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.fefu.fitnesstracker.ui.theme.FitnessTrackerTheme
import ru.fefu.fitnesstracker.R
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import java.nio.file.WatchEvent
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
@Composable
fun Entrace(navController: NavController){
    var login = remember { mutableStateOf("")}
    var password  = remember { mutableStateOf("") }
    var passowrdIsvisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 32.dp, start = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically

        ){
            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "стрелка",
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .size(16.dp, 16.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Вход",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.welcomescreenimage),
                modifier = Modifier.size(271.dp,240.dp),
                contentDescription = "картинка",


                )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                login.value,
                onValueChange = {login.value = it },
                label = {Text("Логин")},
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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                password.value,
                onValueChange = {password.value = it},
                label = {Text("Пароль")},
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily.SansSerif
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if(passowrdIsvisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {passowrdIsvisible=!passowrdIsvisible},modifier = Modifier.size(22.dp,15.dp))
                    {
                        Image(
                            painter = painterResource(id = if(passowrdIsvisible) R.drawable.eye
                            else R.drawable.eye),

                            modifier = Modifier.    size(20.dp),
                            contentDescription = "eye",




                            )

                    }

                }

            )
            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = {navController.navigate("activ-screen")},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Вход",color=Color.White, fontWeight = FontWeight(700), fontFamily =  FontFamily.SansSerif, fontSize = 16.sp)


            }

        }
    }
}