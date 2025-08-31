package ru.fefu.fitnesstracker.DataStore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    var currentUser: User? = null
        private set

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun register(
        username: String,
        name: String,
        password: String,
        repeatPassword: String,
        gender: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            if (password != repeatPassword) {
                onError("Пароли не совпадают")
                return@launch
            }

            val exists = repository.getUserByUsername(username)
            if (exists != null) {
                onError("Пользователь уже существует")
            } else {
                val user = User(username, name, password, gender)
                repository.registerUser(user)
                currentUser = user
                onSuccess()
            }
        }
    }

    fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val user = repository.login(username, password)
            if (user != null) {
                currentUser = user
                onSuccess()
            } else {
                onError("Неверный логин или пароль")
            }
        }
    }
}