package com.ankit.ankitkrsingh_reddit.domain

import com.ankit.ankitkrsingh_reddit.data.model.PopularItemResponse
import com.ankit.ankitkrsingh_reddit.data.model.PopularItemSubResponse
import com.ankit.ankitkrsingh_reddit.data.model.Preview
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
const val LIMIT_POSTS_LIST = 10
const val LIMIT_COMMENT_LIST = 5

class RedditUseCaseInteractor constructor(private val redditRepository: RedditRepository): RedditUseCases{

    override fun getComments(id: String): Single<Any> {
        return redditRepository.getComments(id, LIMIT_COMMENT_LIST)
    }

    override fun getPopularRisingListBy(after: String?): Single<List<PopularItem>> {
        return redditRepository.getPopularRisingList(LIMIT_POSTS_LIST,after).map {
            mapResponseToViewModel(it)
        }
    }

    override fun getPopularHotListBy(after: String?): Single<List<PopularItem>> {
        return redditRepository.getPopularHotList(LIMIT_POSTS_LIST,after).map {
            mapResponseToViewModel(it)
        }
    }


    private fun mapResponseToViewModel(response: PopularItemResponse): ArrayList<PopularItem> {
        val popularItemList =  ArrayList<PopularItem>()
        for(popularItemSubResponse:PopularItemSubResponse in response.data.popularItemList ){
            popularItemList.add(
                PopularItem(
                    popularItemSubResponse.data.title,
                    popularItemSubResponse.data.author,
                    popularItemSubResponse.data.domain,
                    popularItemSubResponse.data.created,
                    //getImage(popularItemSubResponse.data.preview),
                    popularItemSubResponse.data.thumbnail,
                    popularItemSubResponse.data.ups,
                    popularItemSubResponse.data.numComments,
                    response.data.after,
                    popularItemSubResponse.data.id,
                    popularItemSubResponse.data.url
                    )
            )
        }
        return popularItemList
    }

    private fun getImage(preview: Preview?): String {
        if(preview == null) return ""
        if(preview.images.isEmpty()) return ""
        return preview.images[0].source.url
    }
}