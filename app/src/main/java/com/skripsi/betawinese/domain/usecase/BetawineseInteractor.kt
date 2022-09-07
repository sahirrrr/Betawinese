package com.skripsi.betawinese.domain.usecase

import com.skripsi.betawinese.core.data.Resource
import com.skripsi.betawinese.core.data.source.local.entity.DetailEntity
import com.skripsi.betawinese.core.data.source.remote.network.ApiResponse
import com.skripsi.betawinese.core.data.source.remote.request.CommentRequest
import com.skripsi.betawinese.core.data.source.remote.response.BudayaResponse
import com.skripsi.betawinese.core.data.source.remote.response.CommentResponse
import com.skripsi.betawinese.domain.model.CommentModel
import com.skripsi.betawinese.domain.model.DetailModel
import com.skripsi.betawinese.domain.repository.IBetawineseRepository
import io.reactivex.Flowable

class BetawineseInteractor(private val betawineseRepositoryImp: IBetawineseRepository): BetawineseUseCase {
    override fun getListBudaya(): Flowable<ApiResponse<List<BudayaResponse>>> {
        return betawineseRepositoryImp.getListBudaya()
    }

    override fun getListDetail(kodeBudaya: Int): Flowable<Resource<List<DetailModel>>> {
        return betawineseRepositoryImp.getListDetail(kodeBudaya)
    }

    override fun getListComment(kodeBudaya: Int): Flowable<Resource<List<CommentModel>>> {
        return betawineseRepositoryImp.getListComment(kodeBudaya)
    }

    override fun postComment(comment: CommentRequest): Flowable<ApiResponse<CommentResponse>> {
        return betawineseRepositoryImp.postComment(comment)
    }

    override fun getAllFavoriteDetail(): Flowable<List<DetailEntity>> {
        return betawineseRepositoryImp.getAllFavoriteDetail()
    }

    override fun insertFavoriteDetail(detail: DetailModel) {
        return betawineseRepositoryImp.insertFavoriteDetail(detail)
    }
}