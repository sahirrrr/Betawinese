package com.skripsi.betawinese.core.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class CommentRequest(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("kode_budaya")
    val kodeBudaya: Int,

    @field:SerializedName("message")
    val message: String
)