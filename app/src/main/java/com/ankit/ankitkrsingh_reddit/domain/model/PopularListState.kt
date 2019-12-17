package com.ankit.ankitkrsingh_reddit.domain.model

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 16 Dec 2019
 * @version : 1.0
 *
 */
sealed class PopularListState {
    abstract val after:String?
    abstract val loadedAllItems: Boolean
    abstract val data: List<PopularItem>
}
data class DefaultState(override val after: String?, override val loadedAllItems: Boolean, override val data: List<PopularItem>) : PopularListState()
data class LoadingState(override val after: String?, override val loadedAllItems: Boolean, override val data: List<PopularItem>) : PopularListState()
data class PaginatingState(override val after: String?, override val loadedAllItems: Boolean, override val data: List<PopularItem>) : PopularListState()
data class ErrorState(val errorMessage: String, override val after: String?, override val loadedAllItems: Boolean, override val data: List<PopularItem>) : PopularListState()