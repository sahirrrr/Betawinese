package com.skripsi.betawinese.domain.usecase

import com.skripsi.betawinese.core.data.Resource
import com.skripsi.betawinese.core.data.source.local.entity.DetailEntity
import com.skripsi.betawinese.core.data.source.remote.network.ApiResponse
import com.skripsi.betawinese.core.data.source.remote.request.CommentRequest
import com.skripsi.betawinese.core.data.source.remote.response.BudayaResponse
import com.skripsi.betawinese.core.data.source.remote.response.CommentResponse
import com.skripsi.betawinese.core.data.source.remote.response.DetailResponse
import com.skripsi.betawinese.domain.model.CommentModel
import com.skripsi.betawinese.domain.model.DetailModel
import io.reactivex.Flowable

interface BetawineseUseCase {
    fun getListBudaya() : Flowable<ApiResponse<List<BudayaResponse>>>

    fun getListDetail(kodeBudaya: Int) : Flowable<Resource<List<DetailModel>>>

    fun getListComment(kodeBudaya: Int) : Flowable<Resource<List<CommentModel>>>

    fun postComment(comment: CommentRequest) : Flowable<ApiResponse<CommentResponse>>

    fun getAllFavoriteDetail(): Flowable<List<DetailEntity>>

    fun insertFavoriteDetail(detail: DetailModel)
}