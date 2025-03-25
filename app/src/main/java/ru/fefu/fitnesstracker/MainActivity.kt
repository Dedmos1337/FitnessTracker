package ru.fefu.fitnesstracker
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
            startDestination = "activinfo-screen",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("main_screen"){ MainScreen(navController = navController)}
            composable("registration_screen"){Registration(navController = navController)}
            composable ("entrace-screen"){Entrace(navController=navController)}
            composable ("activ-screen"){Activity(navController=navController)}
            composable (
                "activinfo-screen/{activeTime}/{activeType}/{distant}{time}",
                arguments = listOf(
                    navArgument("activeTime") { type = NavType.StringType },
                    navArgument("activeType") { type = NavType.StringType },
                    navArgument("distant") { type = NavType.FloatType },
                    navArgument("time") { type = NavType.StringType }
                )

            )
            {backStackEntry ->
                val activDate = backStackEntry.arguments?.getString("activDate") ?: ""
                val activeTime = backStackEntry.arguments?.getString("activeTime") ?: ""
                val activeType = backStackEntry.arguments?.getString("activeType") ?: ""
                val distant = backStackEntry.arguments?.getDouble("distant") ?: 0.0
                val time = backStackEntry.arguments?.getString("time") ?: ""


                Info_Activ(navController=navController)
            }
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






class MyActiv(private var activ_date: String,private var active_time: String,
               private var active_type: String, private var distant: Double,
               private var time: String){
    fun getActivDate(): String = activ_date
    fun getActiveTime(): String = active_time
    fun getActiveType(): String = active_type
    fun getDistant(): Double = distant
    fun getTime(): String = time

    @Composable
    fun Display(onClick: () -> Unit){
        Column(modifier = Modifier.fillMaxWidth().clickable(onClick=onClick, indication = null, interactionSource = remember { MutableInteractionSource() })){

            Text(
                text = activ_date,
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(400),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth()
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(4.dp), clip = false).clip(RoundedCornerShape(16.dp)).background(Color.White).clickable(onClick=onClick, indication = null, interactionSource = remember { MutableInteractionSource() }))
            {
                Text(
                    text = "$distant км",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                    fontWeight = FontWeight(700),
                    fontSize = 24.sp,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = active_time,
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding( start = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)){
                    Text(
                        text = active_type,
                        modifier = Modifier.size(213.dp,24.dp),
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif

                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = time,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        color = Color.Gray

                    )
                }

            }


        }

        }
    }


@Composable
fun Activity(navController: NavController){
    val coroutineScope = rememberCoroutineScope()
    val state = rememberLazyListState()
    var isSelected1 by remember { mutableStateOf(false) }
    var isSelected2 by remember { mutableStateOf(false) }
    val textColor1 =  Color(0xFF6200EE)
    val textColor2 =  Color(0xFF6200EE)
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 31.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){
            Button(onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor =Color.White),
                modifier = Modifier.size(205.dp,48.dp).drawBehind{

                    drawLine(
                        color = textColor1,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 2.dp.toPx()
                    )

                }


            )
            {
                Text("Моя", color= textColor1, fontSize = 16.sp,
                    fontWeight = FontWeight(500), fontFamily = FontFamily.SansSerif )
            }
            Button(onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor =Color.White),
                modifier = Modifier.size(205.dp,48.dp)



            )
            {
                Text("Пользователей", color= Color.Gray, fontSize = 16.sp,
                    fontWeight = FontWeight(500), fontFamily = FontFamily.SansSerif )
            }

        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp, end = 16.dp, start = 16.dp).height(488.dp),
            state = state,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            val activites = listOf(MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",12.43,"14 часов назад"))
            items(activites) { activity ->
                activity.Display { navController.navigate("activinfo-screen/${activity.getActiveTime()}/${activity.getActiveType()}/${activity.getDistant()}/${activity.getTime()}") }
            }


        }
        Spacer(modifier = Modifier.height(46.dp))

        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End){
            Image(
                painter = painterResource(id= R.drawable.fab),
                contentDescription = "Начать",
                modifier = Modifier.clickable{}.size(64.dp,64.dp)

            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center){
            Column(modifier = Modifier.width(205.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            {
                Image(
                    painter = painterResource(id=R.drawable.sports_activ),
                    contentDescription = "спорт",
                    modifier = Modifier.size(24.dp, 24.dp).clickable{}
                )
                Text(
                    text = "Активность",
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif

                )
            }
            Column(modifier = Modifier.width(205.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            {
                Image(
                    painter = painterResource(id=R.drawable.person_none),
                    contentDescription = "профиль",
                    modifier = Modifier.size(24.dp, 24.dp).clickable{}
                )
                Text(
                    text = "Профиль",
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif

                )
            }
        }





    }

}


@Composable
fun Info_Activ(navController: NavController){
    var text_val by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = {navController.navigate("activ-screen")})
            {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "стрелка",
                    modifier = Modifier.size(24.dp,24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Велосипед",
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically){


                IconButton(onClick = {})
                {
                    Image(
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = "удалить",
                        modifier = Modifier.size(24.dp,24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = {})
                {
                    Image(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = "поделиться",
                        modifier = Modifier.size(24.dp,24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))


            }


        }
        Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp)) {
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "14.32 км",
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 35.sp

            )
            Text(
                text = "14 часов назад",
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 24.sp

            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "1 ч 42 мин",
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



