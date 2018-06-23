package com.navigationdemo.activities

import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.navigationdemo.R


class ProfileActivity : AppCompatActivity() {
    private lateinit var dlDrawer: DrawerLayout
    private lateinit var nvNavigation: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initControl()
    }

    private fun initControl() {
        dlDrawer = findViewById(R.id.activity_profile_dlDrawer)
        val babAppBar = findViewById<BottomAppBar>(R.id.activity_profile_babAppBar)
        nvNavigation = findViewById(R.id.activity_profile_nvNavigation)
        setSupportActionBar(babAppBar)

        val navController = Navigation.findNavController(findViewById(R.id.activity_profile_nav_fragment))

        setupNavigationMenu(navController)

        babAppBar.setNavigationOnClickListener { view ->
            dlDrawer.openDrawer(Gravity.START)
        }
    }

    private fun setupNavigationMenu(navController: NavController) {
        nvNavigation.let { navigationView ->
            NavigationUI.setupWithNavController(navigationView, navController)
        }
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(findViewById(R.id.activity_profile_nav_fragment)).navigateUp()
}