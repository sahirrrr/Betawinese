package com.skripsi.betawinese.core.data.source.local.room

import androidx.room.*
import com.skripsi.betawinese.core.data.source.local.entity.CommentEntity
import com.skripsi.betawinese.core.data.source.local.entity.DetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface BetawineseDao {
    //Detail
    @Query("SELECT * FROM detail_table where kodeBudaya = :kodeBudaya")
    fun getDetailBudaya(kodeBudaya: Int): Flowable<List<DetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailBudaya(detail: List<DetailEntity>): Completable

    //Favorite detail
    @Query("SELECT * FROM detail_table where favorite = :favoriteDetail")
    fun getAllFavoriteBudaya(favoriteDetail : Boolean = true): Flowable<List<DetailEntity>>

    @Update
    fun insertFavoriteBudaya(detail: DetailEntity)

    //Comment
    @Query("SELECT * FROM comment_table where kodeBudaya = :kodeBudaya")
    fun getDetailComment(kodeBudaya: Int): Flowable<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailComment(comment: List<CommentEntity>): Completable
}