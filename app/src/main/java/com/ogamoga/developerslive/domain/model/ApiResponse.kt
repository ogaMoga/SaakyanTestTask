package com.ogamoga.developerslive.domain.model

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("result") val result : List<ApiResult>,
    @SerializedName("totalCount") val totalCount : Int
)

data class ApiResult (
    @SerializedName("id") val id : Int,
    @SerializedName("description") val description : String,
    @SerializedName("gifURL") val gifURL : String,

    @Transient @SerializedName("votes") val votes : Int,
    @Transient @SerializedName("author") val author : String,
    @Transient @SerializedName("date") val date : String,
    @Transient @SerializedName("gifSize") val gifSize : Int,
    @Transient @SerializedName("previewURL") val previewURL : String,
    @Transient @SerializedName("videoURL") val videoURL : String,
    @Transient @SerializedName("videoPath") val videoPath : String,
    @Transient @SerializedName("videoSize") val videoSize : Int,
    @Transient @SerializedName("type") val type : String,
    @Transient @SerializedName("width") val width : Int,
    @Transient @SerializedName("height") val height : Int,
    @Transient @SerializedName("commentsCount") val commentsCount : Int,
    @Transient @SerializedName("fileSize") val fileSize : Int,
    @Transient @SerializedName("canVote") val canVote : Boolean
)