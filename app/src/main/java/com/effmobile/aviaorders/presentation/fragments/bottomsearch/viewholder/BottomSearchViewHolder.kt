package com.effmobile.aviaorders.presentation.fragments.bottomsearch.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.effmobile.aviaorders.databinding.ItemSearchRoutesLayoutBinding
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.CardType
import com.effmobile.aviaorders.presentation.fragments.bottomsearch.model.RouteCardModel
import com.effmobile.aviaorders.resources.R

class BottomSearchViewHolder(
    private val binding: ItemSearchRoutesLayoutBinding
): RecyclerView.ViewHolder(binding.root) {

    var clickCallback: ((item: RouteCardModel) -> Unit)? = null

    fun bind(item: RouteCardModel) = binding.apply {
        isrImage.setImageResource(item.image)
        isrTitleText.text = item.title
        isrDescriptionText.text = item.description
        isrCard.setOnClickListener {
            clickCallback?.invoke(item)
        }
        when(item.cardType) {
            CardType.START -> setupStartCard()
            CardType.MIDDLE -> setupMiddleCard()
            CardType.END -> setupEndCard()
        }
    }

    private fun setupStartCard() = binding.apply {
        isrCard.setBackgroundResource(R.drawable.rounded_top_square)
        isrCard.setPadding(isrCard.paddingLeft, isrCard.paddingTop, isrCard.paddingRight, 0)
    }

    private fun setupMiddleCard() = binding.apply {
        isrCard.setBackgroundResource(R.drawable.square_background)
        isrCard.setPadding(isrCard.paddingLeft, 0, isrCard.paddingRight, 0)
    }

    private fun setupEndCard() = binding.apply {
        isrCard.setBackgroundResource(R.drawable.rounded_bottom_square)
        isrCard.setPadding(isrCard.paddingLeft, 0, isrCard.paddingRight, isrCard.paddingBottom)
    }
}