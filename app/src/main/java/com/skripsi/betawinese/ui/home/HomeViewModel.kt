package com.skripsi.betawinese.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.skripsi.betawinese.domain.usecase.BetawineseUseCase

class HomeViewModel(private val betawineseUseCase: BetawineseUseCase): ViewModel() {
    fun getBudayaList() = LiveDataReactiveStreams.fromPublisher(betawineseUseCase.getListBudaya())
}