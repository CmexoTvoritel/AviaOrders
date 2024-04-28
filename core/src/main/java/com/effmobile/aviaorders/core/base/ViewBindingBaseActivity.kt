package com.effmobile.aviaorders.core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.effmobile.aviaorders.resources.R

@Suppress("UNCHECKED_CAST")
abstract class ViewBindingBaseActivity<VB : ViewBinding>: AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AviaOrders)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(_binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}