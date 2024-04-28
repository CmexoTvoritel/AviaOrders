package com.effmobile.aviaorders.presentation.fragments.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.effmobile.aviaorders.databinding.ItemTicketLayoutBinding
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketModel
import com.effmobile.aviaorders.presentation.fragments.tickets.viewholder.TicketsViewHolder

class TicketsAdapter: ListAdapter<TicketModel, TicketsViewHolder>(TicketsDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        return TicketsViewHolder(binding = ItemTicketLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }
}