package com.codejunction.bookandaar.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.codejunction.bookandaar.BaseActivity
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.repo.DefaultResponse
import com.codejunction.bookandaar.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_sell_books.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellBooks : BaseActivity() {
    lateinit var phoneNum:String
    lateinit var bookName:String
    lateinit var bookDesc:String
    lateinit var bookGenre:String
    lateinit var bookLocation:String
    lateinit var bookArea:String
    lateinit var bookPrice:String
    private lateinit var userName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_books)

        preference=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        sellBackBtn.setOnClickListener {
            onBackPressed()
        }

        //Area List
        val area = arrayOf(
            "Uttam Nagar", "Dawarka", "Rajapuri", "Nawada", "Tilak Nagar","Janakpuri","Shubhash Nagar"
        )

        val areaAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.list_item, area
        )
        tvAreaOfBook.setAdapter(areaAdapter)

        //Genre List
        val genre = arrayOf(
            "Comics", "Novel", "Horror", "Sci-Fi", "Crime","School","College")
        val genreAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.list_item, genre
        )
        tvGenre.setAdapter(genreAdapter)

        radioSellBtn.setOnClickListener {
            etBookPrice.visibility= View.VISIBLE
        }

        radioDonateBtn.setOnClickListener {
            etBookPrice.visibility= View.GONE
        }

        postAdBtn.setOnClickListener {
            phoneNum=preference.getString("PHONE_NUMBER","").toString()
            bookName=tvNameOfBook.text.toString()
            bookDesc=tvDescOfBook.text.toString()
            bookGenre=tvGenre.text.toString()
            bookLocation=tvBookStateLocation.text.toString()
            bookArea=tvAreaOfBook.text.toString()
            userName=preference.getString("FULL_NAME","").toString()

            if (radioDonateBtn.isChecked){
                bookPrice="0"
            }else{
                bookPrice=tvBookPrice.text.toString()
            }


            if (phoneNum.isNotEmpty() && bookName.isNotEmpty() && bookDesc.isNotEmpty()
                && bookGenre.isNotEmpty() && bookLocation.isNotEmpty() && bookArea.isNotEmpty()){

                when {
                    bookName.length>50 -> {
                        errorSnackBar(it,"Book name must be less than 50.")
                        return@setOnClickListener
                    }
                    bookDesc.length>150 -> {
                    errorSnackBar(it,"Description must be less than 150.")
                    return@setOnClickListener

                    }
                    else -> {
                        //snackBar(it,"Ready to start Sell Api")
                        RetrofitClient.instance.postMyAds(userName,phoneNum,bookName,bookDesc,bookGenre,
                            bookLocation,bookArea,bookPrice).enqueue(object :Callback<DefaultResponse>{
                            override fun onResponse(
                                call: Call<DefaultResponse>,
                                response: Response<DefaultResponse>
                            ) {
                                val msg=response.body()?.message
                                snackBar(it,msg.toString())
                                //onBackPressed()
                            }

                            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                                errorSnackBar(it,"Something went wrong! Check Connection")
                            }

                        })
                    }
                }

            }else{
                errorSnackBar(it,"Fill all fields")
            }


        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}