package com.codejunction.bookandartest.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.codejunction.bookandartest.BaseActivity
import com.codejunction.bookandartest.R
import com.codejunction.bookandartest.ui.MainActivity
import com.codejunction.bookandartest.ui.Welcome
import kotlinx.android.synthetic.main.activity_login.*

class Login : BaseActivity() {
    private var phoneNum:String?=null
    private var password:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val contextView = findViewById<View>(R.id.relativeLayout)
        //back Arrow button
        backArrow.setOnClickListener {
            onBackPressed()
        }

        //Don't have an account redirect to signUp
        noAccount.setOnClickListener {
            backToSignUP()
        }


        //Login Button Code
        loginBtn.setOnClickListener {
            phoneNum=userPhoneNumber?.text.toString()
            password=userPassword?.text.toString()
            if (phoneNum!!.isNotEmpty() && password!!.isNotEmpty()){

                if (phoneNum?.length!! < 10){
                    errorSnackBar(contextView,"Enter 10 Digit Number")
                }else{
                    snackBar(it,"Ready to start api")
                }
            }else{
                errorSnackBar(it,"Fill Both Field")
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent= Intent(this,Welcome::class.java)
        startActivity(intent)
        finish()
    }

    private fun backToSignUP(){
        val intent= Intent(this,SignUp::class.java)
        startActivity(intent)
        finish()
    }
}