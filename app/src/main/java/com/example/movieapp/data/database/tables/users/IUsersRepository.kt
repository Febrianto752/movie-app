package com.example.movieapp.data.database.tables.users

import com.example.movieapp.data.models.User
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
    fun getAllUsersStream(): Flow<List<User>>
    fun getUserStream(id: Int): Flow<User?>
    fun getUserByEmailAndPassword(email: String, password: String): Flow<User?>
    fun getUserLogin(): Flow<User?>
    suspend fun insertUser(item: User)
    suspend fun deleteUser(item: User)
    suspend fun updateUser(item: User)
    suspend fun deleteAll()


}