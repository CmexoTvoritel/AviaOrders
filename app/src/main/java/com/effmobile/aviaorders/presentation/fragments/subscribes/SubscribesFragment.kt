package com.effmobile.aviaorders.presentation.fragments.subscribes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.effmobile.aviaorders.core.base.ViewBindingBaseFragment
import com.effmobile.aviaorders.databinding.FragmentSubscribesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscribesFragment : ViewBindingBaseFragment<FragmentSubscribesBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSubscribesBinding
        get() = FragmentSubscribesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}