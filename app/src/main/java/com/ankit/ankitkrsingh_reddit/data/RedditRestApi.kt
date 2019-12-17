package com.ankit.ankitkrsingh_reddit.data

import com.ankit.ankitkrsingh_reddit.data.model.PopularItemResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
interface RedditRestApi{

    @GET("/hot")
    fun getRedditHotList(@Query("limit") limit:Int=10, @Query("after") after: String?) : Single<PopularItemResponse>

    @GET("/rising")
    fun getRedditRisingList(@Query("limit") limit:Int=10, @Query("after") after: String?) : Single<PopularItemResponse>

    @GET("/r/Android/comments/{id}")
    fun getComments(@Path("id") id: String,@Query("limit")limit : Int,@Query("sort")sort: String ="new") : Single<Any>


}