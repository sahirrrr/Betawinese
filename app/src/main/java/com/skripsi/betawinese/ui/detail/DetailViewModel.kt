package com.skripsi.betawinese.ui.detail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.skripsi.betawinese.core.data.source.remote.request.CommentRequest
import com.skripsi.betawinese.domain.model.DetailModel
import com.skripsi.betawinese.domain.usecase.BetawineseUseCase

class DetailViewModel(private val betawineseUseCase: BetawineseUseCase): ViewModel() {
    fun getDetailList(kodeBudaya: Int) = LiveDataReactiveStreams.fromPublisher(betawineseUseCase.getListDetail(kodeBudaya))

    fun setFavoriteDetail(detail: DetailModel) {
        betawineseUseCase.insertFavoriteDetail(detail)
    }

    fun getCommentList(kodeBudaya: Int) = LiveDataReactiveStreams.fromPublisher(betawineseUseCase.getListComment(kodeBudaya))

    fun postComment(comment: CommentRequest) = LiveDataReactiveStreams.fromPublisher(betawineseUseCase.postComment(comment))
}