package com.example.movieapp.data.database.tables.movie_favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.data.models.MovieFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieFavoriteDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(item: MovieFavorite)

        @Delete
        suspend fun delete(item: MovieFavorite)

        @Query("SELECT * from movie_favorites WHERE id = :id")
        fun getMovieFavorite(id: Int): Flow<MovieFavorite?>

        @Query("SELECT * from movie_favorites ORDER BY title ASC")
        fun getAllMovieFavorites(): Flow<List<MovieFavorite>>

        @Query("DELETE FROM movie_favorites")
        fun deleteAll()

}