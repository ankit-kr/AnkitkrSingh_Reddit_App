package com.ankit.ankitkrsingh_reddit.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */

data class PopularItemResponse(
    @Expose @SerializedName("data") val data : DataResponse
)

data class DataResponse(
    @Expose @SerializedName("children") val popularItemList: List<PopularItemSubResponse>,
    @Expose @SerializedName("after") val after: String?
)

data class PopularItemSubResponse(
    @Expose @SerializedName("kind") val kind : String,
    @Expose @SerializedName("data") val data : PopularItemData
)

data class PopularItemData(
    @Expose @SerializedName("title") val title : String,
    @Expose @SerializedName("author") val author : String,
    @Expose @SerializedName("domain") val domain : String,
    @Expose @SerializedName("created") val created : Long,
    @Expose @SerializedName("preview") val preview : Preview,
    @Expose @SerializedName("thumbnail") val thumbnail : String,
    @Expose @SerializedName("ups") val ups : Int,
    @Expose @SerializedName("num_comments") val numComments : Int,
    @Expose @SerializedName("id") val id : String,
    @Expose @SerializedName("url") val url : String
)

data class Preview(
    @Expose @SerializedName("images")val images : List<Image>
)

data class Image(
    @Expose @SerializedName("source")val source : Source
)
data class Source(
    @Expose @SerializedName("url")val url : String
)
