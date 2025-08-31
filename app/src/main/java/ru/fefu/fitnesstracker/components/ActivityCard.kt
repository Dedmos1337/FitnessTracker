package ru.fefu.fitnesstracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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