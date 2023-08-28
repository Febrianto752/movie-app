package com.example.movieapp.data.database.tables.users

import com.example.movieapp.data.models.User
import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository(private val userDao: UserDao) : IUsersRepository {
    override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()
    override fun getUserStream(id: Int): Flow<User?> = userDao.getUser(id)
    override fun getUserByEmailAndPassword(email: String, password: String): Flow<User?> =
        userDao.getUserByEmailAndPassword(email, password)

    override fun getUserLogin(): Flow<User?> = userDao.getUserLogin()
    override suspend fun insertUser(item: User) = userDao.insert(item)
    override suspend fun deleteUser(item: User) = userDao.delete(item)
    override suspend fun updateUser(item: User) = userDao.update(item)
    override suspend fun deleteAll() = userDao.deleteAll()
}