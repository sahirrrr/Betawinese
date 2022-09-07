package com.skripsi.betawinese.core.data

import com.skripsi.betawinese.core.data.source.local.LocalDataSource
import com.skripsi.betawinese.core.data.source.local.entity.DetailEntity
import com.skripsi.betawinese.core.data.source.remote.RemoteDataSource
import com.skripsi.betawinese.core.data.source.remote.network.ApiResponse
import com.skripsi.betawinese.core.data.source.remote.request.CommentRequest
import com.skripsi.betawinese.core.data.source.remote.response.BudayaResponse
import com.skripsi.betawinese.core.data.source.remote.response.CommentResponse
import com.skripsi.betawinese.core.data.source.remote.response.DetailResponse
import com.skripsi.betawinese.core.utils.DataMapper
import com.skripsi.betawinese.domain.model.CommentModel
import com.skripsi.betawinese.domain.model.DetailModel
import com.skripsi.betawinese.domain.repository.IBetawineseRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BetawineseRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : IBetawineseRepository {
    override fun getListBudaya(): Flowable<ApiResponse<List<BudayaResponse>>> {
        return remoteDataSource.getListBudaya() }

    override fun getListDetail(kodeBudaya: Int): Flowable<Resource<List<DetailModel>>> =
        object : NetworkBoundResource<List<DetailModel>, List<DetailResponse>>() {
            override fun loadFromDB(): Flowable<List<DetailModel>> {
                return localDataSource.getDetailBudaya(kodeBudaya).map { DataMapper.mapGetListDetailToDomain(it) }
            }

            override fun shouldFetch(data: List<DetailModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: List<DetailResponse>) {
                val detailList = DataMapper.mapDetailListResponseToEntitites(data)
                localDataSource.insertDetailBudaya(detailList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<DetailResponse>>> {
                return remoteDataSource.getListDetail(kodeBudaya)
            }
        }.asFlowAble()

    override fun getListComment(kodeBudaya: Int): Flowable<Resource<List<CommentModel>>> =
        object : NetworkBoundResource<List<CommentModel>, List<CommentResponse>>() {
            override fun loadFromDB(): Flowable<List<CommentModel>> {
                return localDataSource.getDetailComment(kodeBudaya).map { DataMapper.mapGetListCommentToDomain(it) }
            }

            override fun shouldFetch(data: List<CommentModel>?): Boolean {
                return true
            }

            override fun saveCallResult(data: List<CommentResponse>) {
                val commentList = DataMapper.mapCommentListResponseToEntities(data)
                localDataSource.insertDetailComment(commentList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<CommentResponse>>> {
                return remoteDataSource.getListComment(kodeBudaya)
            }
        }.asFlowAble()

override fun postComment(comment: CommentRequest): Flowable<ApiResponse<CommentResponse>> {
        return remoteDataSource.postListComment(comment)
    }

    override fun getAllFavoriteDetail(): Flowable<List<DetailEntity>> {
        return localDataSource.getAllFavoriteBudaya()
    }

    override fun insertFavoriteDetail(detail: DetailModel) {
        localDataSource.insertFavoriteBudaya(
            DetailEntity(
                creator = detail.creator,
                name = detail.name,
                kodeBudaya = detail.kodeBudaya,
                description = detail.description,
                photo = detail.photo,
                favorite = detail.favorite,
                id = detail.id
            )
        )
    }
}