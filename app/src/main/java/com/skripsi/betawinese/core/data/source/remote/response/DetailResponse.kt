package com.skripsi.betawinese.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(
	@field:SerializedName("creator")
	val creator: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("kode_budaya")
	val kodeBudaya: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("id")
	val id: Int
)

