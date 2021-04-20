package com.codejunction.bookandaar.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.codejunction.bookandaar.BaseActivity
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.fragments.Home
import com.codejunction.bookandaar.fragments.MyAds
import com.codejunction.bookandaar.models.HomeProductModel
import com.codejunction.bookandaar.models.LoginResponse
import com.codejunction.bookandaar.models.MyAdsModel
import com.codejunction.bookandaar.network.RetrofitClient
import com.codejunction.bookandaar.ui.MainActivity
import com.codejunction.bookandaar.ui.Welcome
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : BaseActivity() {
    lateinit var phoneNum:String
    lateinit var password:String
    lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreference=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)


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
            phoneNum=userPhoneNumber?.text?.trim().toString()
            password=userPassword?.text?.trim().toString()

            if (phoneNum.isNotEmpty() && password.isNotEmpty()){

                if (phoneNum.length < 10){
                    errorSnackBar(contextView,"Enter 10 Digit Number")
                }else{
                    snackBar(it,"Ready to start api")
                    RetrofitClient.instance.login(phoneNum,password).enqueue(object :Callback<LoginResponse>{
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            toaster(t.message.toString())
                            Log.i("faizan",t.message.toString())
                        }

                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            //Toast.makeText(applicationContext,response.body()?.message.toString(),Toast.LENGTH_LONG).show()
                            //Log.i("faizanali",response.body()?.message.toString())
                            val msg=response.body()?.message
                            if (msg.equals("user found")){
                                Toast.makeText(applicationContext,"True",Toast.LENGTH_LONG).show()
                                val editor:SharedPreferences.Editor=sharedPreference.edit()
                                editor.putString("PHONE_NUMBER",phoneNum)
                                editor.putString("PASSWORD",password)
                                editor.putString("LOGIN","true")
                                editor.apply()
                                loginSuccessful()
                            }else{
                                Toast.makeText(applicationContext,"false",Toast.LENGTH_LONG).show()
                            }

                        }

                    })
                }
            }else{
                errorSnackBar(it,"Fill Both Field")
//                val intent=Intent(this,MainActivity::class.java)
//                startActivity(intent)
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

    private fun loginSuccessful(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

