package com.effmobile.aviaorders.presentation.fragments.bottomsearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.effmobile.aviaorders.databinding.ItemSearchRoutesLayoutBinding
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.RouteCardModel
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.viewholder.BottomSearchViewHolder

class BottomSearchAdapter: ListAdapter<RouteCardModel, BottomSearchViewHolder>(BottomSearchDiffUtils()) {

    var clickCallback: ((item: RouteCardModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSearchViewHolder {
        return BottomSearchViewHolder(binding = ItemSearchRoutesLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: BottomSearchViewHolder, position: Int) {
        holder.bind(item = currentList[position])
        holder.clickCallback = clickCallback
    }
}