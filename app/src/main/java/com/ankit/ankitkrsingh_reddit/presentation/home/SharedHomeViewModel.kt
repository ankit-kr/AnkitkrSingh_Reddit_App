package com.ankit.ankitkrsingh_reddit.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ankit.ankitkrsingh_reddit.domain.LIMIT_POSTS_LIST
import com.ankit.ankitkrsingh_reddit.domain.RedditUseCases
import com.ankit.ankitkrsingh_reddit.domain.model.*
import com.ankit.ankitkrsingh_reddit.presentation.base.BaseViewModel
import com.ankit.ankitkrsingh_reddit.presentation.common.Constants
import com.ankit.ankitkrsingh_reddit.presentation.common.Event
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SharedHomeViewModel @Inject constructor(
    private val redditUseCases: RedditUseCases
): BaseViewModel() {

    companion object{
        private val TAG = SharedHomeViewModel::class.java.simpleName
    }

    private lateinit var commentItemList: ObservableArrayList<Any>

    val stateLiveData =  MutableLiveData<PopularListState>()

    var isLastPostsPage: Boolean = false
    var isPostsLoading: Boolean = false

    private lateinit var popularItemList : ObservableArrayList<PopularItem>

    enum class Route{
        POPULAR_DETAIL,
        SHARE,
        MENU_POPUP,
        COMMENT_MENU_POPUP
    }

    enum class Filter{
        HOT,
        RISING
    }

    val currentFilter = ObservableField(Filter.HOT) //hot is defult
    val selectedPopularItem = ObservableField<PopularItem>()

    private val _routeEvent = MutableLiveData<Event<Pair<Route,Bundle>>>()
    val routeEvent : LiveData<Event<Pair<Route,Bundle>>> = _routeEvent

    private fun setCurrentPopularItem(selectedItem: PopularItem){
        selectedPopularItem.set(selectedItem)
    }

    fun setFilter(newFilter: Filter){
        if(newFilter.name == currentFilter.get()!!.name) return
        currentFilter.set(newFilter)
    }

    private fun triggerRouteEvent(pair: Pair<Route, Bundle>){
        _routeEvent.postValue(Event(pair))
    }

    fun onPopularItemClick(popularItem: PopularItem) {
        setCurrentPopularItem(popularItem)
        triggerRouteEvent(Pair(Route.POPULAR_DETAIL, Bundle.EMPTY))
    }

    fun getRedditPopularHotPosts(after: String?) {
        Log.d(TAG, "after id hot: $after")
        redditUseCases.getPopularHotListBy(after)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<List<PopularItem>>{
                override fun onSuccess(t: List<PopularItem>) {
                    handleOnSuccessResponse(t)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    val afterId = obtainCurrentAfterId()
                    stateLiveData.value = ErrorState(e.message ?: "", afterId, obtainCurrentLoadedAllItems(), obtainCurrentData())
                }
            })
    }

    fun getRedditPopularRisingPosts(after: String?) {
        Log.d(TAG, "after id rising: $after")
        redditUseCases.getPopularRisingListBy(after)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<List<PopularItem>>{
                override fun onSuccess(t: List<PopularItem>) {
                    handleOnSuccessResponse(t)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    val afterId = obtainCurrentAfterId()
                    stateLiveData.value = ErrorState(e.message ?: "", afterId, obtainCurrentLoadedAllItems(), obtainCurrentData())
                }
            })
    }

    private fun handleOnSuccessResponse(popularItemList: List<PopularItem>) {
        removeLoadingItem()
        this.popularItemList.addAll(popularItemList)
        val currentAfterId = if(popularItemList.isEmpty()){
            null
        }else{
            popularItemList[0].after
        }
        val areAllItemsLoaded = popularItemList.size < LIMIT_POSTS_LIST
        stateLiveData.value = DefaultState(currentAfterId, areAllItemsLoaded, this.popularItemList)
    }

    private fun removeLoadingItem() {
        if(popularItemList.isEmpty()) return
        if(popularItemList[popularItemList.size-1].isLoading){
            popularItemList.removeAt(popularItemList.size-1)
        }
    }

    fun initDataList(popularItemList: ObservableArrayList<PopularItem>) {
        stateLiveData.value = DefaultState(null, false, popularItemList)
        this.popularItemList = popularItemList
    }

    fun onRefresh() {
        popularItemList.clear()
        stateLiveData.value = LoadingState(null, false, popularItemList)
        when(currentFilter.get()){
            Filter.RISING ->{
                getRedditPopularRisingPosts(null)
            }
            else ->{
                getRedditPopularHotPosts(null)
            }
        }
    }

    fun onMenuClick(){
        triggerRouteEvent(Pair(Route.MENU_POPUP, Bundle.EMPTY))
    }

    fun onCommentMenuClick(){
        triggerRouteEvent(Pair(Route.COMMENT_MENU_POPUP,Bundle.EMPTY))
    }

    fun updatePopularPostsList() {
        val afterId = obtainCurrentAfterId()
        stateLiveData.value = if (afterId == null)
            LoadingState(afterId, false, obtainCurrentData())
        else
            PaginatingState(afterId, false, obtainCurrentData())

        when(currentFilter.get()){
            Filter.RISING ->{
                getRedditPopularRisingPosts(afterId)
            }
            else ->{
                getRedditPopularHotPosts(afterId)
            }
        }
    }


    private fun obtainCurrentAfterId() = stateLiveData.value?.after

    private fun obtainCurrentData() = stateLiveData.value?.data ?: popularItemList

    private fun obtainCurrentLoadedAllItems() = stateLiveData.value?.loadedAllItems ?: false
    fun initCommentsDataList(commentItemList: ObservableArrayList<Any>) {
        this.commentItemList = commentItemList
    }

    fun getCommentList() {
        redditUseCases.getComments(selectedPopularItem.get()!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Any>{
                override fun onSuccess(t: Any) {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }

    fun onPostShareClick(){
        triggerRouteEvent(Pair(Route.SHARE,Bundle().apply {
            putString(Constants.POST_URL, selectedPopularItem.get()!!.url)
        }))
    }
}
