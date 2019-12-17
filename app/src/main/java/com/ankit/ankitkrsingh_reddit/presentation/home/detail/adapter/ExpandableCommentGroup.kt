package com.ankit.ankitkrsingh_reddit.presentation.home.detail.adapter

import com.ankit.ankitkrsingh_reddit.domain.model.CommentItem
import com.xwray.groupie.ExpandableGroup

class ExpandableCommentGroup constructor(
        comment : CommentItem,
        depth : Int = 0) : ExpandableGroup(ExpandableCommentItem(comment, depth)) {

    init {
//        for (comm in comment.children) {
//            add(ExpandableCommentGroup(comm, depth.plus(1)))
//        }
    }

}
