package com.effmobile.aviaorders.presentation.fragments.tickets.adapter

import androidx.recyclerview.widget.DiffUtil
import com.effmobile.aviaorders.presentation.fragments.tickets.model.TicketModel

class TicketsDiffUtils: DiffUtil.ItemCallback<TicketModel>() {
    override fun areItemsTheSame(oldItem: TicketModel, newItem: TicketModel): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: TicketModel, newItem: TicketModel): Boolean {
        return oldItem.badge == newItem.badge &&
                oldItem.price == newItem.price &&
                oldItem.arrivalAirport == newItem.arrivalAirport &&
                oldItem.arrivalDate == newItem.arrivalDate &&
                oldItem.departureAirport == newItem.departureAirport &&
                oldItem.departureDate == newItem.departureDate &&
                oldItem.timeFlight == newItem.timeFlight &&
                oldItem.hasTransfer == newItem.hasTransfer
    }
}