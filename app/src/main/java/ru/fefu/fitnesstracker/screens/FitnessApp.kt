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
import ru.fefu.fitnesstracker.screens.MainScreen
import ru.fefu.fitnesstracker.screens.Registration
import ru.fefu.fitnesstracker.screens.Entrace
import ru.fefu.fitnesstracker.screens.Info_Activ
import ru.fefu.fitnesstracker.screens.Activity
import ru.fefu.fitnesstracker.components.MyActiv
import ru.fefu.fitnesstracker.components.UsersActiv
import ru.fefu.fitnesstracker.screens.Users_Activity
import ru.fefu.fitnesstracker.screens.Info_Users_Activity


@Composable
fun FitnessApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->
        NavHost(
            navController= navController,
            startDestination ="main_screen",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("main_screen"){ MainScreen(navController = navController)}
            composable("registration_screen"){Registration(navController = navController)}
            composable ("entrace-screen"){Entrace(navController=navController)}
            composable ("activ-screen"){Activity(navController=navController)}
            composable (
                "activinfo-screen/{activeTime}/{activeType}/{distant}/{time}",
                arguments = listOf(
                    navArgument("activeTime") { type = NavType.StringType },
                    navArgument("activeType") { type = NavType.StringType },
                    navArgument("distant") { type = NavType.FloatType },
                    navArgument("time") { type = NavType.StringType }
                )

            )
            {backStackEntry ->
                val activeTime = backStackEntry.arguments?.getString("activeTime") ?: ""
                val activeType = backStackEntry.arguments?.getString("activeType") ?: ""
                val distant = backStackEntry.arguments?.getFloat("distant")?.toDouble() ?: 0.0
                val time = backStackEntry.arguments?.getString("time") ?: ""


                Info_Activ(navController, activeTime, activeType, distant, time)
            }
            composable ("activuser-screen"){Users_Activity(navController=navController)}

            composable (
                "info-activuser-screen/{useractivetime}/{useractivetype}/{userdistant}/{usertime}/{usertag}",
                arguments = listOf(
                    navArgument("useractivetime"){ type = NavType.StringType},
                    navArgument("useractivetype"){ type = NavType.StringType},
                    navArgument("userdistant"){ type = NavType.FloatType},
                    navArgument("usertime"){ type = NavType.StringType},
                    navArgument("usertag"){ type = NavType.StringType}
                )
            ){
                    backStackEntry ->
                val useractivetime = backStackEntry.arguments?.getString("useractivetime") ?: ""
                val useractivetype = backStackEntry.arguments?.getString("useractivetype") ?: ""
                val userdistant = backStackEntry.arguments?.getFloat("userdistant")?.toDouble() ?: 0.0
                val usertime = backStackEntry.arguments?.getString("usertime") ?: ""
                val usertag = backStackEntry.arguments?.getString("usertag") ?: ""
                Info_Users_Activity(navController,useractivetime, useractivetype, userdistant, usertime, usertag)  }
        }

    }
}