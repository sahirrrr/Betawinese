package com.skripsi.betawinese.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_table")
data class CommentEntity(
    @PrimaryKey
    val id: Int,

    val kodeBudaya: Int,

    val name: String,

    val message: String
)