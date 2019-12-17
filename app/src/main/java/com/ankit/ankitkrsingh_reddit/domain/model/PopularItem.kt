package com.ankit.ankitkrsingh_reddit.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
@Parcelize
data class PopularItem(
    val title : String,
    val author : String,
    val domain: String,
    val created : Long,
    val thumbnail :String,
    val ups : Int,
    val numComments : Int,
    val after: String?, //common field used for pagination
    val id: String, //comments sub url
    val url : String,
    val isLoading: Boolean = false

) : Parcelable
