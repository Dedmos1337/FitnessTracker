package ru.fefu.fitnesstracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.fefu.fitnesstracker.components.MyActiv
import ru.fefu.fitnesstracker.R

@Composable
fun Activity(navController: NavController){
    val coroutineScope = rememberCoroutineScope()
    val state = rememberLazyListState()
    val textColor1 =  Color(0xFF6200EE)
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
            Button(onClick = {navController.navigate("activuser-screen")},
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
            val activites = listOf(MyActiv("Вчера","1 часа 41 минут","Бег",1.0,"11 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",1.43,"13 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",1.43,"13 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",1.43,"13 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",1.43,"13 часов назад"),
                MyActiv("Вчера","2 часа 46 минут","Серфинг",1.43,"13 часов назад"))
            items(activites) { activity ->
                activity.Display{navController.navigate("activinfo-screen/${activity.getActiveTime()}/${activity.getActiveType()}/${activity.getDistant()}/${activity.getTime()}")}
            }


        }
        Spacer(modifier = Modifier.height(46.dp))

        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End){
            Image(
                painter = painterResource(id= R.drawable.fab),
                contentDescription = "Начать",
                modifier = Modifier.clickable{}.size(128.dp,128.dp)

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