package com.example.movieapp.ui.viewModels.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.database.tables.users.IUsersRepository
import com.example.movieapp.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class UserViewModel(private val usersRepository: IUsersRepository) : ViewModel() {
    var usersList by mutableStateOf<List<User>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            var users = usersRepository.getAllUsersStream()
            users.collect { userListParam ->
                usersList = userListParam;
            }
        }
    }

    suspend fun updateUserList(){
        var users = usersRepository.getAllUsersStream()
        users.collect{userListParam ->
            usersList = userListParam;
        }
    }

    fun getUserLogin(): Flow<User?>{
        return usersRepository.getUserLogin()
    }

    fun login(email: String, password: String): Flow<User?> {
        return usersRepository.getUserByEmailAndPassword(email, password)
    }

    suspend fun createUser(user: User) {
        usersRepository.insertUser(user)
        updateUserList()
    }

    suspend fun updateUser(user: User){
        usersRepository.updateUser(user)
        updateUserList()
    }

    suspend fun deleteUser(user: User){
        usersRepository.deleteUser(user)
        updateUserList()
    }

    suspend fun deleteAllUser(){
        usersRepository.deleteAll()
        updateUserList()
    }


}