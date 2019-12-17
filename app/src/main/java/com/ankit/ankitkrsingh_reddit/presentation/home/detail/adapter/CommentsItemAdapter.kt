package com.ankit.ankitkrsingh_reddit.presentation.home.detail.adapter

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 16 Dec 2019
 * @version : 1.0
 *
 */
private const val LOADING_VIEW_TYPE = 0
private const val ITEM_VIEW_TYPE = 1

//class CommentsItemAdapter constructor(items: ObservableArrayList<CommentItem>, private val requestManager: RequestManager)
//    : ObservableRecyclerViewAdapter<CommentItem, RecyclerView.ViewHolder>(items){
//
//    private var isLoadingViewAdded = false
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when(viewType){
//            LOADING_VIEW_TYPE -> {
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_footer_item, parent, false)
//                LoadingViewHolder(view)
//            }
//            else ->{
//                val binding = DataBindingUtil
//                    .inflate<ItemPopularBinding>(LayoutInflater.from(parent.context), R.layout.item_comment,parent,false)
//                PopularItemViewHolder(binding,onItemClickListener)
//            }
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        try{
//            (holder as PopularItemViewHolder).bind(getItem(position),requestManager)
//        }catch (e: Exception){}
//    }
//
//    override fun getItemViewType(position: Int) =
//        if (position == getItems().size -1 && isLoadingViewAdded) LOADING_VIEW_TYPE else ITEM_VIEW_TYPE
//
//
//
//    fun removeLoadingViewFooter() {
//        if (isLoadingViewAdded && getItems().size > 0) {
//            isLoadingViewAdded = false
//            getItems().removeAt(getItems().size-1)
//            //notifyItemRemoved(getItems().size)
//        }
//    }
//
//    fun addLoadingViewFooter(emptyDataObject: CommentItem = CommentItem(",","",0,"")) {
//        if (getItems().size > 0) {
//            isLoadingViewAdded = true
//            getItems().add(emptyDataObject)
//            //notifyItemInserted(getItems().size - 1)
//        }
//    }
//}