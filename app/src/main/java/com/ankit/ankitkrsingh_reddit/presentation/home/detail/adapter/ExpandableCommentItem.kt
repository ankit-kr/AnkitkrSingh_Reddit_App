package com.ankit.ankitkrsingh_reddit.presentation.home.detail.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import com.ankit.ankitkrsingh_reddit.R
import com.ankit.ankitkrsingh_reddit.domain.model.CommentItem
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_expandable_comment.view.*

open class ExpandableCommentItem constructor(
    private val comment : CommentItem,
    private val depth : Int) : Item<GroupieViewHolder>(), ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        addingDepthViews(viewHolder)

        viewHolder.itemView.tv_user.text = comment.author
        viewHolder.itemView.body.text = Html.fromHtml(comment.body)
        viewHolder.itemView.tv_votes.text = comment.score
        viewHolder.itemView.apply {
            setOnLongClickListener {
                expandableGroup.onToggleExpanded()
                true
            }
        }
    }

    private fun addingDepthViews(viewHolder: GroupieViewHolder) {
        viewHolder.itemView.separatorContainer.removeAllViews()
        viewHolder.itemView.separatorContainer.visibility =
                if (depth > 0) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        for (i in 1..depth) {
            val v : View = LayoutInflater.from(viewHolder.itemView.context)
                    .inflate(R.layout.layout_separator_view, viewHolder.itemView.separatorContainer, false)
            viewHolder.itemView.separatorContainer.addView(v)
        }
        viewHolder.itemView.body.requestLayout()
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }

    override fun getLayout(): Int {
        return R.layout.item_expandable_comment
    }

}