package com.skripsi.betawinese.ui.like

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.skripsi.betawinese.domain.usecase.BetawineseUseCase

class LikeViewModel(private val betawineseUseCase: BetawineseUseCase): ViewModel() {
    fun getFavoriteList() = LiveDataReactiveStreams.fromPublisher(betawineseUseCase.getAllFavoriteDetail())
}