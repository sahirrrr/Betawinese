package com.skripsi.betawinese.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_table")
data class DetailEntity(

    val creator: String,

    val name: String,

    val kodeBudaya: Int,

    val description: String,

    val photo: String,

    var favorite :Boolean = false,

    @PrimaryKey
    val id: Int
)