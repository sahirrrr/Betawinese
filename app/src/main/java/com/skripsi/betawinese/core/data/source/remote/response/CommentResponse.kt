package com.skripsi.betawinese.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("kode_budaya")
	val kodeBudaya: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("message")
	val message: String
)

