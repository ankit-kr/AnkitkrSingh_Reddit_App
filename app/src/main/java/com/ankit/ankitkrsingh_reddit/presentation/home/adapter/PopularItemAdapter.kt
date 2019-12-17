package com.ankit.ankitkrsingh_reddit.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.ankit.ankitkrsingh_reddit.R
import com.ankit.ankitkrsingh_reddit.databinding.ItemPopularBinding
import com.ankit.ankitkrsingh_reddit.domain.model.PopularItem
import com.ankit.ankitkrsingh_reddit.presentation.common.ObservableRecyclerViewAdapter
import com.bumptech.glide.RequestManager

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
private const val LOADING_VIEW_TYPE = 0
private const val ITEM_VIEW_TYPE = 1

class PopularItemAdapter constructor(items: ObservableArrayList<PopularItem>, private val requestManager: RequestManager)
    : ObservableRecyclerViewAdapter<PopularItem, RecyclerView.ViewHolder>(items){

    private var isLoadingViewAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            LOADING_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_footer_item, parent, false)
                LoadingViewHolder(view)
            }
            else ->{
                val binding = DataBindingUtil
                    .inflate<ItemPopularBinding>(LayoutInflater.from(parent.context), R.layout.item_popular,parent,false)
                PopularItemViewHolder(binding,onItemClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try{
            (holder as PopularItemViewHolder).bind(getItem(position),requestManager)
        }catch (e: Exception){}
    }

    override fun getItemViewType(position: Int) =
        if (position == getItems().size -1 && isLoadingViewAdded) LOADING_VIEW_TYPE else ITEM_VIEW_TYPE



    fun removeLoadingViewFooter() {
        if (isLoadingViewAdded && getItems().size > 0) {
            isLoadingViewAdded = false
            getItems().removeAt(getItems().size-1)
            //notifyItemRemoved(getItems().size)
        }
    }

    fun addLoadingViewFooter(emptyDataObject: PopularItem = PopularItem("","","",0,"",0,0,null,"","",true)) {
        if (getItems().size > 0) {
            isLoadingViewAdded = true
            getItems().add(emptyDataObject)
            //notifyItemInserted(getItems().size - 1)
        }
    }
}

class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)