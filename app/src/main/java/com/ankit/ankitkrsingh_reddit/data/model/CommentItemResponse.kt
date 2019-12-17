package com.ankit.ankitkrsingh_reddit.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 17 Dec 2019
 * @version : 1.0
 * 
 */
class CommentItemResponse (
    @Expose
    @SerializedName("data") val data : CommentDataResponse
)

data class CommentDataResponse(
    @Expose @SerializedName("children") val commentItemList: List<CommentItemSubResponse>,
    @Expose @SerializedName("after") val after: String?
)

data class CommentItemSubResponse(
    @Expose @SerializedName("kind") val kind : String,
    @Expose @SerializedName("data") val data : PopularItemData
)

data class CommentItemData(
    @Expose @SerializedName("title") val title : String,
    @Expose @SerializedName("body") val body : String,
    @Expose @SerializedName("domain") val domain : String,
    @Expose @SerializedName("created") val created : Long,
    @Expose @SerializedName("preview") val preview : Preview,
    @Expose @SerializedName("thumbnail") val thumbnail : String,
    @Expose @SerializedName("ups") val ups : Int,
    @Expose @SerializedName("num_comments") val numComments : Int,
    @Expose @SerializedName("id") val id : String
)
