package com.codejunction.bookandaar.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.auth.Login
import com.codejunction.bookandaar.auth.SignUp
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {
    lateinit var preference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        preference=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val isLogin=preference.getString("LOGIN","")
        if (isLogin=="true"){
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
        }

        welcomeLoginBtn.setOnClickListener {
            val intent=Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }
        welcomeSignUpBtn.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()

        }
    }
}