package com.ankit.ankitkrsingh_reddit.data

import com.ankit.ankitkrsingh_reddit.data.model.PopularItemResponse
import com.ankit.ankitkrsingh_reddit.domain.RedditRepository
import io.reactivex.Single

/**
 *<h1></h1>
 *
 *<p>RedditRepositoryImpl</p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
class RedditRepositoryImpl (private val redditRestApi: RedditRestApi) : RedditRepository{
    override fun getComments(id: String,limit: Int):Single<Any> {
        return redditRestApi.getComments(id, limit)
    }

    override fun getPopularHotList(limit: Int, after: String?): Single<PopularItemResponse> {
        return redditRestApi.getRedditHotList(limit,after)
    }

    override fun getPopularRisingList(
        limit: Int,
        after: String?
    ): Single<PopularItemResponse> {
        return redditRestApi.getRedditRisingList(limit,after)
    }
}