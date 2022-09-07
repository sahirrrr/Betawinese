package com.skripsi.betawinese.core.data.source.remote.network

import com.skripsi.betawinese.core.data.source.remote.request.CommentRequest
import com.skripsi.betawinese.core.data.source.remote.response.BudayaResponse
import com.skripsi.betawinese.core.data.source.remote.response.CommentResponse
import com.skripsi.betawinese.core.data.source.remote.response.DetailResponse
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("budaya")
    fun getListBudaya() : Flowable<List<BudayaResponse>>

    @GET("budaya/detail/{kode_budaya}")
    fun getListDetail(@Path("kode_budaya") kodeBudaya: Int) : Flowable<List<DetailResponse>>

    @GET("budaya/detail/comment/{kode_budaya}")
    fun getListComment(@Path("kode_budaya") kodeBudaya: Int) : Flowable<List<CommentResponse>>

    @POST("budaya/detail/comment")
    fun postComment(@Body comment: CommentRequest) : Flowable<CommentResponse>
}