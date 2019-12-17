package com.ankit.ankitkrsingh_reddit.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ankit.ankitkrsingh_reddit.databinding.ItemPopularBinding
import com.ankit.ankitkrsingh_reddit.domain.model.PopularItem
import com.bumptech.glide.RequestManager

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
class PopularItemViewHolder constructor(
    private val binding: ItemPopularBinding,
    private val onClickListener: (any: Any)-> Unit
): RecyclerView.ViewHolder(binding.root){

    fun bind(popularItem : PopularItem, requestManager: RequestManager){
        binding.popularItem = popularItem
        binding.root.setOnClickListener {
            onClickListener.invoke(popularItem)
        }
        requestManager.load(popularItem.thumbnail).into(binding.ivPopular)

    }
}