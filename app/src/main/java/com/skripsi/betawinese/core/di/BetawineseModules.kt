package com.skripsi.betawinese.core.di

import androidx.room.Room
import com.skripsi.betawinese.core.data.BetawineseRepository
import com.skripsi.betawinese.core.data.source.local.LocalDataSource
import com.skripsi.betawinese.core.data.source.local.room.BetawineseDB
import com.skripsi.betawinese.core.data.source.remote.RemoteDataSource
import com.skripsi.betawinese.core.data.source.remote.network.ApiService
import com.skripsi.betawinese.domain.repository.IBetawineseRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<BetawineseDB>().betawineseDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            BetawineseDB::class.java, "betawinese_db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val betawineseModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get())}
    single<IBetawineseRepository> { BetawineseRepository( get(), get()) }
}