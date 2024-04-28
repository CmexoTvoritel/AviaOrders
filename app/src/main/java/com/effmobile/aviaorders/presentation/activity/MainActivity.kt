package com.effmobile.aviaorders.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.effmobile.aviaorders.core.base.ViewBindingBaseActivity
import com.effmobile.aviaorders.databinding.ActivityMainBinding
import com.effmobile.aviaorders.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ViewBindingBaseActivity<ActivityMainBinding>() {

    private var currentDestinationId = -1
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavController()
        setupBottomNavigation()
        setupNavGraph(savedInstanceState = savedInstanceState)
    }

    private fun setupNavController() {
        navHostFragment = supportFragmentManager.findFragmentById(
            R.id.amFragmentContainer
        ) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupBottomNavigation() = binding.apply {
        amBottomNavigationView.bnbBottomNav.setupWithNavController(navController)
        amBottomNavigationView.bnbBottomNav.apply {
            itemIconTintList = null
        }
    }

    private fun setupNavGraph(savedInstanceState: Bundle?) {
        val graph = navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph)
        runCatching {
            navController.setGraph(graph, null)
        }.getOrNull() ?: navController.restoreState(
            savedInstanceState?.getBundle(
                KEY_RESTORE_NAV_STATE
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(KEY_RESTORE_NAV_STATE, navController.saveState())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        navController.restoreState(savedInstanceState.getBundle(KEY_RESTORE_NAV_STATE))
    }

    companion object {
        private const val KEY_RESTORE_NAV_STATE = "nav_state"
    }
}