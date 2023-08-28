package com.example.movieapp.data.database

import android.content.Context
import com.example.movieapp.data.database.tables.movie_favorites.IMovieFavoritesRepository
import com.example.movieapp.data.database.tables.movie_favorites.OfflineMovieFavoritesRepository
import com.example.movieapp.data.database.tables.users.IUsersRepository
import com.example.movieapp.data.database.tables.users.OfflineUsersRepository

interface AppContainer {
    val usersRepository: IUsersRepository
    val movieFavoritesRepository: IMovieFavoritesRepository

}

/**
 * [AppContainer] implementation that provides instance of [OfflineUsersRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [UsersRepository]
     */
    override val usersRepository: IUsersRepository by lazy {
        OfflineUsersRepository(MovieAppDatabase.getDatabase(context).userDao())
    }

    override val movieFavoritesRepository: IMovieFavoritesRepository by lazy{
        OfflineMovieFavoritesRepository(MovieAppDatabase.getDatabase(context).movieFavoriteDao())
    }

}
