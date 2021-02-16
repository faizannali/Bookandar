package com.codejunction.bookandartest

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity: AppCompatActivity() {

    fun toaster(msg:String){
        Toast.makeText(applicationContext,msg,Toast.LENGTH_LONG).show()
    }

    fun snackBar(v:View,msg: String){
        val snackBar = Snackbar.make(
            v, msg,
            Snackbar.LENGTH_LONG
        )
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(getColor(R.color.darkBlue))
        snackBar.show()
    }

    fun errorSnackBar(v:View,msg: String){
        val snackBar = Snackbar.make(
            v, msg,
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.RED)
        snackBar.show()

    }
}