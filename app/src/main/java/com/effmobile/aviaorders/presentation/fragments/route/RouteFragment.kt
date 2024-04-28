package com.effmobile.aviaorders.presentation.fragments.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.effmobile.aviaorders.core.base.ViewBindingBaseFragment
import com.effmobile.aviaorders.databinding.FragmentRouteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteFragment : ViewBindingBaseFragment<FragmentRouteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRouteBinding
        get() = FragmentRouteBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}