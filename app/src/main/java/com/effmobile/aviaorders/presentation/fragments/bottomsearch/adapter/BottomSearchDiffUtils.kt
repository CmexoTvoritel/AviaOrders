package com.effmobile.aviaorders.presentation.fragments.bottomsearch.adapter

import androidx.recyclerview.widget.DiffUtil
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.RouteCardModel

class BottomSearchDiffUtils: DiffUtil.ItemCallback<RouteCardModel>() {
    override fun areItemsTheSame(oldItem: RouteCardModel, newItem: RouteCardModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RouteCardModel, newItem: RouteCardModel): Boolean {
        return oldItem.cardType == newItem.cardType &&
                oldItem.title == newItem.title &&
                oldItem.description == newItem.description &&
                oldItem.image == newItem.image
    }
}