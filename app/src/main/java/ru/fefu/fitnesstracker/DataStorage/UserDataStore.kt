package ru.fefu.fitnesstracker.DataStorage
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "user_prefs")


class UserDataStore(private val context: Context){
    companion object{
        private val USSERNAME = stringPreferencesKey("username")
        private  val PASSWORD = stringPreferencesKey("password")
        private val LOGIN = stringPreferencesKey("login")
        private val GENDER = stringPreferencesKey("gender")
    }

    suspend fun SaveUserData(credentials: UserCredentials){
        context.dataStore.edit { preferences ->
            preferences[USSERNAME] = credentials.username
            preferences[PASSWORD] = credentials.password
            preferences[LOGIN] = credentials.login
            preferences[GENDER] = credentials.gender
        }

    }
    val userCredentials:Flow<UserCredentials?> = context.dataStore.data.map{preferences->
        val username = preferences[USSERNAME] ?: return@map null
        val password = preferences[PASSWORD] ?: return@map null
        val login = preferences[LOGIN] ?: return@map null
        val gender = preferences[LOGIN] ?: return@map null


        UserCredentials(username, password, login, gender)

    }




}