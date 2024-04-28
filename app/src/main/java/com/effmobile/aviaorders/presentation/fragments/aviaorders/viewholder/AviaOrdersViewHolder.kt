package com.effmobile.aviaorders.presentation.fragments.aviaorders.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.effmobile.aviaorders.databinding.ItemAviaOrderCardLayoutBinding
import com.effmobile.aviaorders.presentation.fragments.aviaorders.model.Offer
import com.effmobile.aviaorders.resources.R

class AviaOrdersViewHolder(
    private val binding: ItemAviaOrderCardLayoutBinding
): RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: Offer) = binding.apply {
        iaocTitleText.text = item.title
        iaocTownText.text = item.town
        iaocPriceText.text = "от " + formattedPriceStr(price = item.price.value) + " ₽"
        when(item.id) {
            1 -> { iaocImageCard.setImageResource(R.drawable.ic_offers_placeholder_first) }
            2 -> { iaocImageCard.setImageResource(R.drawable.ic_offers_placeholder_second) }
            3 -> { iaocImageCard.setImageResource(R.drawable.ic_offers_placeholder_third) }
        }
    }

    private fun formattedPriceStr(price: Int): String {
        return String.format("%,d", price).replace(",", " ")
    }
}