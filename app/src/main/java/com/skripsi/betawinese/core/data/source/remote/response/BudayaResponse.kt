package com.skripsi.betawinese.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BudayaResponse(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("id")
	var id: Int,

	@field:SerializedName("color")
	val color: String
) : Parcelable

