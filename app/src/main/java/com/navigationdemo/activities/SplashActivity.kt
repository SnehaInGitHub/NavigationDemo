package com.navigationdemo.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.navigationdemo.R

class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_splash)
        executeHandler()
    }

    private fun executeHandler() {
        handler = Handler()
        val INTERVAL: Long = 3000
        handler.postDelayed(runnable, INTERVAL)
    }

    private val runnable = Runnable {
        val homeIntent = Intent(this, LoginActivity::class.java)
        startActivity(homeIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}
