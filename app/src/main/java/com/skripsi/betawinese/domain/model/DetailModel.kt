package com.skripsi.betawinese.domain.model

import com.google.gson.annotations.SerializedName

data class DetailModel(
    val creator: String,

    val name: String,

    val kodeBudaya: Int,

    val description: String,

    val photo: String,

    var favorite :Boolean,

    val id: Int
    )