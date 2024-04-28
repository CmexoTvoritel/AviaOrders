package com.effmobile.aviaorders.presentation.fragments.tickets.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.effmobile.aviaorders.databinding.ItemTicketLayoutBinding
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketModel

class TicketsViewHolder(
    private val binding: ItemTicketLayoutBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TicketModel) = binding.apply {
        itTicketBadge.visibility = if(item.badge != null) View.VISIBLE else View.GONE
        itTicketDescription.text = item.badge ?: ""
        itTicketCost.text = item.price
        itTimeStart.text = item.departureDate
        itTimeEnd.text = item.arrivalDate
        itFromAbbreviation.text = item.departureAirport
        itToAbbreviation.text = item.arrivalAirport
        itTimeFlight.text = item.timeFlight
        itTransferSlash.visibility = if(item.hasTransfer) View.GONE else View.VISIBLE
        itTransferState.visibility = if(item.hasTransfer) View.GONE else View.VISIBLE
    }
}