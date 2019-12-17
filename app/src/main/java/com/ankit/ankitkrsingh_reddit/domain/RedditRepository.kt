package com.ankit.ankitkrsingh_reddit.domain

import com.ankit.ankitkrsingh_reddit.data.model.PopularItemResponse
import io.reactivex.Single

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
interface RedditRepository{

    fun getPopularHotList(limit:Int, after: String?) : Single<PopularItemResponse>
    fun getPopularRisingList(limit:Int, after: String?) : Single<PopularItemResponse>
    fun getComments(id: String, limit: Int): Single<Any>
}