package com.navigationdemo.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.navigationdemo.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onSupportNavigateUp() = findNavController(findViewById(R.id.nav_host_fragment_new)).navigateUp()
}