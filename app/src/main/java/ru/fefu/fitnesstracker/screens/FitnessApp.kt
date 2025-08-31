package ru.fefu.fitnesstracker.screens
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.fefu.fitnesstracker.DataStore.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import android.app.Application
import androidx.compose.ui.platform.LocalContext


@Composable
fun FitnessApp() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext as Application

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main_screen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("main_screen") {
                MainScreen(navController = navController)
            }

            composable("registration_screen") {
                val viewModel: AuthViewModel = viewModel(
                    factory = AndroidViewModelFactory.getInstance(context)
                )
                Registration(
                    viewModel = viewModel,
                    onRegisterSuccess = {
                        // После регистрации возвращаемся на главный экран (или на экран входа)
                        navController.navigate("main_screen") {
                            popUpTo("registration_screen") { inclusive = true }
                        }
                    },
                    navController = navController
                )
            }

            composable("entrace-screen") {
                val viewModel: AuthViewModel = viewModel(
                    factory = AndroidViewModelFactory.getInstance(context)
                )
                Entrace(
                    viewModel = viewModel,
                    navController = navController
                )
            }

            composable("activ-screen") {
                Activity(navController = navController)
            }

            composable(
                "activinfo-screen/{activeTime}/{activeType}/{distant}/{time}",
                arguments = listOf(
                    navArgument("activeTime") { type = NavType.StringType },
                    navArgument("activeType") { type = NavType.StringType },
                    navArgument("distant") { type = NavType.FloatType },
                    navArgument("time") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val activeTime = backStackEntry.arguments?.getString("activeTime") ?: ""
                val activeType = backStackEntry.arguments?.getString("activeType") ?: ""
                val distant = backStackEntry.arguments?.getFloat("distant")?.toDouble() ?: 0.0
                val time = backStackEntry.arguments?.getString("time") ?: ""

                Info_Activ(navController, activeTime, activeType, distant, time)
            }

            composable("activuser-screen") {
                Users_Activity(navController = navController)
            }

            composable(
                "info-activuser-screen/{useractivetime}/{useractivetype}/{userdistant}/{usertime}/{usertag}",
                arguments = listOf(
                    navArgument("useractivetime") { type = NavType.StringType },
                    navArgument("useractivetype") { type = NavType.StringType },
                    navArgument("userdistant") { type = NavType.FloatType },
                    navArgument("usertime") { type = NavType.StringType },
                    navArgument("usertag") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val useractivetime = backStackEntry.arguments?.getString("useractivetime") ?: ""
                val useractivetype = backStackEntry.arguments?.getString("useractivetype") ?: ""
                val userdistant = backStackEntry.arguments?.getFloat("userdistant")?.toDouble() ?: 0.0
                val usertime = backStackEntry.arguments?.getString("usertime") ?: ""
                val usertag = backStackEntry.arguments?.getString("usertag") ?: ""

                Info_Users_Activity(navController, useractivetime, useractivetype, userdistant, usertime, usertag)
            }
        }
    }
}