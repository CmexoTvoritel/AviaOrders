package com.effmobile.aviaorders.presentation.fragments.aviaorders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.effmobile.aviaorders.databinding.ItemAviaOrderCardLayoutBinding
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.Offer
import com.effmobile.aviaorders.presentation.fragments.aviaorders.viewholder.AviaOrdersViewHolder

class AviaOrdersAdapter: ListAdapter<Offer, AviaOrdersViewHolder>(AviaOrdersDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AviaOrdersViewHolder {
        return AviaOrdersViewHolder(binding = ItemAviaOrderCardLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: AviaOrdersViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }
}