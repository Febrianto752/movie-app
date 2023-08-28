package com.example.movieapp.data.database.tables.movie_favorites

import com.example.movieapp.data.models.MovieFavorite
import kotlinx.coroutines.flow.Flow


class OfflineMovieFavoritesRepository(private val bookFavoriteDao: MovieFavoriteDao) :
    IMovieFavoritesRepository {
    override fun getAllMovieFavoritesStream(): Flow<List<MovieFavorite>> =
        bookFavoriteDao.getAllMovieFavorites()

    override fun getMovieFavoriteStream(id: Int): Flow<MovieFavorite?> =
        bookFavoriteDao.getMovieFavorite(id)

    override suspend fun insertMovieFavorite(item: MovieFavorite) = bookFavoriteDao.insert(item)
    override suspend fun deleteMovieFavorite(item: MovieFavorite) = bookFavoriteDao.delete(item)
    override suspend fun deleteAll() = bookFavoriteDao.deleteAll()

}