package com.codejunction.bookandaar

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity: AppCompatActivity() {

    lateinit var preference: SharedPreferences
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

    fun logOut(){
        preference=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor=preference.edit()
        editor.putString("LOGIN","false")
        //editor.remove("PHONE_NUMBER")
        editor.clear()
        editor.apply()
    }

}