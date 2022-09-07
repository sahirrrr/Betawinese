package com.skripsi.betawinese.core.utils

import com.skripsi.betawinese.core.data.source.local.entity.CommentEntity
import com.skripsi.betawinese.core.data.source.local.entity.DetailEntity
import com.skripsi.betawinese.core.data.source.remote.response.BudayaResponse
import com.skripsi.betawinese.core.data.source.remote.response.CommentResponse
import com.skripsi.betawinese.core.data.source.remote.response.DetailResponse
import com.skripsi.betawinese.domain.model.BudayaModel
import com.skripsi.betawinese.domain.model.CommentModel
import com.skripsi.betawinese.domain.model.DetailModel

object DataMapper {
    fun mapGetListBudayaToDomain(data: List<BudayaResponse>): List<BudayaModel> {
        return data.map {
            with(it) {
                BudayaModel(
                    name, description, photo, id
                )
            }
        }
    }

    fun mapGetListDetailToDomain(data: List<DetailEntity>): List<DetailModel> {
        return data.map {
            with(it) {
                DetailModel(
                    creator, name, kodeBudaya, description, photo, favorite, id
                )
            }
        }
    }

    fun mapDetailListResponseToEntitites(data : List<DetailResponse>) : List<DetailEntity> {
        return data.map {
            with(it) {
                DetailEntity(
                    creator, name, kodeBudaya, description, photo, false, id
                )
            }
        }
    }

    fun mapGetListCommentToDomain(data: List<CommentEntity>): List<CommentModel> {
        return data.map {
            with(it) {
                CommentModel(
                    id, kodeBudaya, name, message
                )
            }
        }
    }

    fun mapCommentListResponseToEntities(data: List<CommentResponse>): List<CommentEntity> {
        return data.map {
            with(it) {
                CommentEntity(
                    id, kodeBudaya, name, message
                )
            }
        }
    }

    fun mapCommentResponseToDomain(data: CommentResponse) : CommentModel {
        return CommentModel(
            data.id,
            data.kodeBudaya,
            data.name,
            data.message
        )
    }
}