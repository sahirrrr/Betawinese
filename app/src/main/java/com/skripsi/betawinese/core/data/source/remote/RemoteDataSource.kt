package com.skripsi.betawinese.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.skripsi.betawinese.core.data.source.remote.network.ApiResponse
import com.skripsi.betawinese.core.data.source.remote.network.ApiService
import com.skripsi.betawinese.core.data.source.remote.request.CommentRequest
import com.skripsi.betawinese.core.data.source.remote.response.BudayaResponse
import com.skripsi.betawinese.core.data.source.remote.response.CommentResponse
import com.skripsi.betawinese.core.data.source.remote.response.DetailResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class RemoteDataSource(private val apiService: ApiService) {

    fun getListBudaya() : Flowable<ApiResponse<List<BudayaResponse>>> {
        val responseResult = PublishSubject.create<ApiResponse<List<BudayaResponse>>>()
        val client = apiService.getListBudaya()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(if (response.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getListDetail(kodeBudaya: Int) : Flowable<ApiResponse<List<DetailResponse>>> {
        val responseResult = PublishSubject.create<ApiResponse<List<DetailResponse>>>()
        val client = apiService.getListDetail(kodeBudaya)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(if (response.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getListComment(kodeBudaya: Int) : Flowable<ApiResponse<List<CommentResponse>>> {
        val responseResult = PublishSubject.create<ApiResponse<List<CommentResponse>>>()
        val client = apiService.getListComment(kodeBudaya)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(if (response.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun postListComment(comment: CommentRequest) : Flowable<ApiResponse<CommentResponse>> {
        val responseResult = PublishSubject.create<ApiResponse<CommentResponse>>()
        val client = apiService.postComment(comment)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(ApiResponse.Success(response))
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }
}