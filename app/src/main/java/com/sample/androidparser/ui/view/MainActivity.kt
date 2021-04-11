package com.sample.androidparser.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sample.androidparser.R


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val listener =
        NavController.OnDestinationChangedListener { _, destination, _ ->

            if (destination.id != R.id.nav_parser) {
                supportActionBar?.setHomeButtonEnabled(true)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow_black)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onResume() {
        super.onResume()
//        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
//        navController.removeOnDestinationChangedListener(listener)
    }
}