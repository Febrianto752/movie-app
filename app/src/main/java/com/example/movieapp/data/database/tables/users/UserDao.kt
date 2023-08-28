package com.example.movieapp.data.database.tables.users

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.data.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: User)

    @Update
    suspend fun update(item: User)

    @Delete
    suspend fun delete(item: User)

    @Query("SELECT * from users WHERE id = :id")
    fun getUser(id: Int): Flow<User?>

    @Query("SELECT * from users WHERE email = :email AND password = :password")
    fun getUserByEmailAndPassword(email: String, password: String): Flow<User?>

    @Query("SELECT * from users WHERE isLogin = :isLogin")
    fun getUserLogin(isLogin: Boolean = true): Flow<User?>

    @Query("SELECT * from users ORDER BY name ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("DELETE FROM users")
    fun deleteAll()
}