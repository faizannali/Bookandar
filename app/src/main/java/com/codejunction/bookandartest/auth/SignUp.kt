package com.codejunction.bookandartest.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.codejunction.bookandartest.BaseActivity
import com.codejunction.bookandartest.R
import com.codejunction.bookandartest.ui.Welcome
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : BaseActivity() {
    private var name:String?=null
    private var email:String?=null
    private var location:String?=null
    private var phoneNumber:String?=null
    private var passwd:String?=null
    private var area:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



        alreadyAccount.setOnClickListener {
            backToLogin()
        }

        signUpBtn.setOnClickListener {
            name=tvName?.text.toString()
            email=tvEmail?.text.toString()
            location=tvLocation?.text.toString()
            phoneNumber=tvPhone?.text.toString()
            passwd=tvPassword?.text.toString()
//            area=dropDown?.text.toString()

            if (name!!.isNotEmpty() && email!!.isNotEmpty() && location!!.isNotEmpty()
                && phoneNumber!!.isNotEmpty() && passwd!!.isNotEmpty()){

                if (phoneNumber?.length!! < 10){

                    errorSnackBar(it,"Enter 10 Digit Number")
                }else{
                    snackBar(it,"Ready to start Singup Api")
                }
            }else{
                errorSnackBar(it,"Fill all fields")
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent= Intent(this,Welcome::class.java)
        startActivity(intent)
        finish()
    }

    private fun backToLogin(){
        val intent= Intent(this,Login::class.java)
        startActivity(intent)
        finish()
    }
}