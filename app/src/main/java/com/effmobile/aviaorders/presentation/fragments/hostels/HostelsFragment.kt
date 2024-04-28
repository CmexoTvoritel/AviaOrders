package com.effmobile.aviaorders.presentation.fragments.hostels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.effmobile.aviaorders.core.base.ViewBindingBaseFragment
import com.effmobile.aviaorders.databinding.FragmentHostelsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostelsFragment : ViewBindingBaseFragment<FragmentHostelsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHostelsBinding
        get() = FragmentHostelsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}