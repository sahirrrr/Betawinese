package com.skripsi.betawinese.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skripsi.betawinese.core.data.source.local.entity.CommentEntity
import com.skripsi.betawinese.core.data.source.local.entity.DetailEntity

@Database(entities = [DetailEntity::class, CommentEntity::class], version = 2, exportSchema = false)
abstract class BetawineseDB : RoomDatabase() {

    abstract fun betawineseDao() : BetawineseDao
}