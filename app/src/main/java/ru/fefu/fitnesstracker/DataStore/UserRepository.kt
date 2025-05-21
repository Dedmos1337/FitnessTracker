package ru.fefu.fitnesstracker.DataStore

import ru.fefu.fitnesstracker.DataStore.User
import ru.fefu.fitnesstracker.DataStore.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(user: User): Boolean {
        val existingUser = userDao.getUserByUsername(user.username)
        return if (existingUser == null) {
            userDao.insertUser(user)
            true
        } else {
            false
        }
    }

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }
}
