package ru.fefu.fitnesstracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.fefu.fitnesstracker.R
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.fefu.fitnesstracker.DataStore.AuthViewModel
import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory

@Composable
fun Entrace(navController: NavController, viewModel: AuthViewModel){
    val context = LocalContext.current.applicationContext
    var login = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var passowrdIsvisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    LaunchedEffect(Unit) {
        val savedLogin = sharedPrefs.getString("login", null)
        val savedPassword = sharedPrefs.getString("password", null)
        if (!savedLogin.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {

            login.value = savedLogin
            password.value = savedPassword
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                modifier = Modifier.size(271.dp, 240.dp),
                contentDescription = "картинка"
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = login.value,
                onValueChange = { login.value = it },
                label = { Text("Логин") },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily.SansSerif
                ),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color(0xFFCCCCCC)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Пароль") },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily.SansSerif
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passowrdIsvisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = { passowrdIsvisible = !passowrdIsvisible },
                        modifier = Modifier.size(22.dp, 15.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                id = if (passowrdIsvisible) R.drawable.eye else R.drawable.eye
                            ),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "eye"
                        )
                    }
                }
            )

            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    errorMessage = null
                    isLoading = true

                    viewModel.login(
                        username = login.value,
                        password = password.value,
                        onSuccess = {
                            sharedPrefs.edit()
                                .putString("login", login.value)
                                .putString("password", password.value)
                                .apply()

                            isLoading = false
                            navController.navigate("activ-screen") {
                                // очищаем стек, чтобы назад не вернуться на экран входа
                                popUpTo("entrace-screen") { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        onError = {
                            isLoading = false
                            errorMessage = it
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                enabled = !isLoading
            ){
                Text(
                    "Вход",
                    color = Color.White,
                    fontWeight = FontWeight(700),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp
                )
            }
        }
    }
}