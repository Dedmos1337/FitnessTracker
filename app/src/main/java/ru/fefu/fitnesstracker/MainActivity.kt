package ru.fefu.fitnesstracker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessTrackerTheme {
                FitnessApp()
            }
        }
    }
}
@Composable
fun FitnessApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->
        NavHost(
            navController= navController,
            startDestination = "main_screen",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("main_screen"){ MainScreen(navController = navController)}
            composable("registration_screen"){Registration(navController = navController)}
            composable ("entrace-screen"){Entrace(navController=navController)}
        }

    }
}

@Composable
 fun MainScreen(navController: NavController) {
    val fontSize = 16.sp
    val lineHeight = fontSize * 1.5f

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Image(
            painter = painterResource(id = R.drawable.welcomescreenimage),
            contentDescription = "велосипеды",
            modifier = Modifier.size(379.dp,335.dp)

        )
        Spacer(
            modifier = Modifier.height(32.dp))

        Text(
            text = "Пожалуй, лучший фитнес трекер в ДВФУ",
            fontSize = 24.sp,
            fontWeight = FontWeight(weight = 700),
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(376.dp)
        )


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text =  "Созданный студентами",
            fontWeight = FontWeight(400),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,

        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            onClick = {navController.navigate("registration_screen")},
            colors = ButtonDefaults.buttonColors(containerColor =Color(0xFF6200EE)),
            modifier = Modifier.size(218.dp,48.dp),
            shape = RoundedCornerShape(4.dp)
            ){ Text("Зарегистрироваться",color=Color.White, fontWeight = FontWeight( 700), fontSize = 16.sp,) }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {navController.navigate("entrace-screen")},
            colors = ButtonDefaults.buttonColors(containerColor =Color.White)


        )
        { Text("Уже есть аккаунт?", color = Color(0xFF6200EE), fontWeight = FontWeight(700), fontSize = fontSize, lineHeight = lineHeight) }

    }
}

@Composable
fun Registration(navController: NavController) {
    var text = remember { mutableStateOf("") }
    var name = remember {mutableStateOf("")  }
    var password =remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword = remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var selectOption by remember { mutableStateOf("Мужской") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp)
    ) {

        Row(
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
                text = "Регистрация",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
        }


        Spacer(modifier = Modifier.height(32.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.SansSerif
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color(0xFFCCCCCC)
                ),
                label = { Text("Логин") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                name.value,
                onValueChange = { name.value = it },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color(0xFFCCCCCC)
                ),
                label = { Text("Имя или никнейм") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                password.value,
                onValueChange = {password.value= it},
                label = { Text("Пароль") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {passwordVisible = !passwordVisible },
                        modifier = Modifier.size(22.dp,15.dp))
                    {
                        Image(
                            painter = painterResource(
                                id = if (passwordVisible)
                                    R.drawable.eye
                                else
                                    R.drawable.eye
                            ),
                            contentDescription = if (passwordVisible) "Скрыть пароль" else "Показать пароль",
                            modifier = Modifier.size(20.dp)
                        )

                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                confirmPassword.value,
                onValueChange = {confirmPassword.value = it},
                label = {Text("Повторите пароль")},
                visualTransformation = if(confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {confirmPasswordVisible=!confirmPasswordVisible},modifier = Modifier.size(22.dp,15.dp)) {
                        Image(
                            painter = painterResource(
                                id = if (passwordVisible)
                                    R.drawable.eye
                                else
                                    R.drawable.eye
                            ),
                            contentDescription = if (passwordVisible) "Скрыть пароль" else "Показать пароль",
                            modifier = Modifier.size(20.dp)

                        )
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

//
//            if(password != confirmPassword){
//                Text(
//                    text = "Пароли не совпадают",
//                    color = Color.Red,
//                    style = MaterialTheme.typography.bodySmall,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
//
//            }
//            else {
//
//                )
//
//            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )


            Text(
                text="Пол",
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column{
                listOf("Мужской","Женский","Другой").forEach { option ->
                   Row(
                       verticalAlignment = Alignment.CenterVertically

                   ){
                       RadioButton(
                           selected = (option == selectOption),
                           onClick = {selectOption = option},
                           colors = RadioButtonDefaults.colors(
                               selectedColor = Color(0xFF6200EE)
                           )
                       )
                       Text(option)
                   }
                }

            }

            Spacer(modifier = Modifier.height(33.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)

            ) {
                Text("Зарегистрироваться", color= Color.White, fontSize = 16.sp,
                    fontWeight = FontWeight(700), fontFamily = FontFamily.SansSerif )

            }
            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = "Нажимая на кнопку, вы соглашаетесь \n" +
                        "с политикой конфиденциальности и обработки персональных данных, а" +
                        " также принимаете пользовательское соглашение",
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )


            }
        }
    }

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
                onClick = {/**/},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Вход",color=Color.White, fontWeight = FontWeight(700), fontFamily =  FontFamily.SansSerif, fontSize = 16.sp)


            }

        }
    }
}




