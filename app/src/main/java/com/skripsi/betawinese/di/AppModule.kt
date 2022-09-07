package com.skripsi.betawinese.di

import com.skripsi.betawinese.domain.usecase.BetawineseInteractor
import com.skripsi.betawinese.domain.usecase.BetawineseUseCase
import com.skripsi.betawinese.ui.detail.DetailViewModel
import com.skripsi.betawinese.ui.home.HomeViewModel
import com.skripsi.betawinese.ui.like.LikeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<BetawineseUseCase> { BetawineseInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { LikeViewModel(get()) }
}
