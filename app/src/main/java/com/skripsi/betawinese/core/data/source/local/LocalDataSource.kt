package com.skripsi.betawinese.core.data.source.local

import com.skripsi.betawinese.core.data.source.local.entity.CommentEntity
import com.skripsi.betawinese.core.data.source.local.entity.DetailEntity
import com.skripsi.betawinese.core.data.source.local.room.BetawineseDao
import io.reactivex.Flowable
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(private val dao: BetawineseDao) {
    fun getDetailBudaya(kodeBudaya: Int): Flowable<List<DetailEntity>> = dao.getDetailBudaya(kodeBudaya)

    fun insertDetailBudaya(detail: List<DetailEntity>) = dao.insertDetailBudaya(detail)

    fun getAllFavoriteBudaya(): Flowable<List<DetailEntity>> = dao.getAllFavoriteBudaya()

    fun insertFavoriteBudaya(detail: DetailEntity) {
            dao.insertFavoriteBudaya(detail)
    }

    fun getDetailComment(kodeBudaya: Int): Flowable<List<CommentEntity>> = dao.getDetailComment(kodeBudaya)

    fun insertDetailComment(comment: List<CommentEntity>) = dao.insertDetailComment(comment)
}