package com.example.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.database.tables.movie_favorites.MovieFavoriteDao
import com.example.movieapp.data.database.tables.users.UserDao
import com.example.movieapp.data.models.MovieFavorite
import com.example.movieapp.data.models.User

@Database(entities = [User::class, MovieFavorite::class], version = 1, exportSchema = false)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun movieFavoriteDao(): MovieFavoriteDao

    companion object {
        @Volatile
        private var Instance: MovieAppDatabase? = null

        fun getDatabase(context: Context): MovieAppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieAppDatabase::class.java, "movies_db")
                    .build().also { Instance = it }
            }
        }
    }
}