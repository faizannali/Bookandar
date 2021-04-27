package com.codejunction.bookandaar.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codejunction.bookandaar.BaseActivity
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.fragments.Home
import com.codejunction.bookandaar.models.SignUpModel
import com.codejunction.bookandaar.network.RetrofitClient
import com.codejunction.bookandaar.ui.MainActivity
import com.codejunction.bookandaar.ui.Welcome
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : BaseActivity() {
    lateinit var  name: String
    lateinit var email:String
    lateinit var location:String
    private lateinit var phoneNumber:String
    private lateinit var passwd:String
    lateinit var area:String
    lateinit var sharedPreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sharedPreference=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        alreadyAccount.setOnClickListener {
            backToLogin()
        }

        signUpBtn.setOnClickListener {
            name=tvName.text.toString()
            email=tvEmail?.text.toString().trim()
            location=tvLocation?.text.toString()
            phoneNumber=tvPhone?.text.toString().trim()
            passwd=tvPassword?.text.toString().trim()
//            area=dropDown?.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && location.isNotEmpty()
                && phoneNumber.isNotEmpty() && passwd.isNotEmpty()){

                if (phoneNumber.length < 10){

                    errorSnackBar(it,"Enter 10 Digit Number")
                }else{
                    snackBar(it,"Ready to start Singup Api")
                    RetrofitClient.instance.signUp(name,email,location,phoneNumber,passwd).enqueue(object :Callback<SignUpModel>{
                        override fun onResponse(
                            call: Call<SignUpModel>,
                            response: Response<SignUpModel>
                        ) {
                            //Toast.makeText(applicationContext,response.body()?.message,Toast.LENGTH_LONG).show()
                            Log.i("faizanali",response.body()?.message.toString())
                            val msg=response.body()?.message
                            val error=response.body()?.errno
                            when {
                                msg.equals("User registered successfully") -> {
                                    toaster("true")
                                    val editor:SharedPreferences.Editor=sharedPreference.edit()
                                    editor.putString("PHONE_NUMBER",phoneNumber)
                                    editor.putString("PASSWORD",passwd)
                                    editor.putString("FULL_NAME",name)
                                    editor.putString("LOGIN","true")
                                    editor.apply()
                                    //signUpSuccessful()
                                }
                                error==1062 -> {
                                    toaster("User already exist")
                                }
                            }
                      }

                        override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                            toaster(t.message.toString())
                            errorSnackBar(it,"Check internet Connection !")
                        }

                    })
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

    private fun signUpSuccessful(){
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}