package com.codejunction.bookandartest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.codejunction.bookandartest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController=findNavController(R.id.hostFragment)
        bottom_navigation.setupWithNavController(navController)

        nestedScroll.setOnScrollChangeListener { v, _, _, _, oldScrollY ->
            if (v.scrollY > oldScrollY) {
                sellBook.hide();
            } else if (v.scrollX < oldScrollY || v.scrollY <= 0) {
                sellBook.show();
            }
//            oldScrollY = v.scrollY
        }

        sellBook.setOnClickListener {
            openSellActivity()
        }

    }

    private fun openSellActivity(){
        val intent=Intent(this,SellBooks::class.java)
        startActivity(intent)
        finish()
    }



}