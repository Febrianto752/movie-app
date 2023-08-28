package com.example.movieapp.data.database.tables.movie_favorites

import com.example.movieapp.data.models.MovieFavorite
import kotlinx.coroutines.flow.Flow


interface IMovieFavoritesRepository {
    fun getAllMovieFavoritesStream(): Flow<List<MovieFavorite>>
    fun getMovieFavoriteStream(id: Int): Flow<MovieFavorite?>
    suspend fun insertMovieFavorite(item: MovieFavorite)
    suspend fun deleteMovieFavorite(item: MovieFavorite)
    suspend fun deleteAll()
}