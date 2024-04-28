package com.effmobile.aviaorders.presentation.fragments.aviaorders.adapter

import androidx.recyclerview.widget.DiffUtil
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.Offer

class AviaOrdersDiffUtils: DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
                && oldItem.town == newItem.town
                && oldItem.title == newItem.title
                && oldItem.price.value == newItem.price.value
    }

}