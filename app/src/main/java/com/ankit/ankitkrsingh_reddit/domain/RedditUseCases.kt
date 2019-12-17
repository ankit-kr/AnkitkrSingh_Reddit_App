package com.ankit.ankitkrsingh_reddit.domain

import com.ankit.ankitkrsingh_reddit.domain.model.PopularItem
import io.reactivex.Single

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
interface RedditUseCases{

    fun getPopularHotListBy(after:String?): Single<List<PopularItem>>

    fun getPopularRisingListBy(after:String?): Single<List<PopularItem>>

    fun getComments(id: String): Single<Any>
}