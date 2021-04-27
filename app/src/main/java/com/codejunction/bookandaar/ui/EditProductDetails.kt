package com.codejunction.bookandaar.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isNotEmpty
import com.codejunction.bookandaar.BaseActivity
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.network.RetrofitClient
import com.codejunction.bookandaar.repo.DefaultResponse
import kotlinx.android.synthetic.main.activity_edit_product_details.*
import kotlinx.android.synthetic.main.activity_sell_books.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProductDetails : BaseActivity() {
    private lateinit var nameOfProduct: String
    private lateinit var descriptionOfProduct: String
    private lateinit var genreOfProduct: String
    private lateinit var areaOfProduct: String
    private lateinit var priceOfProduct: String
    private lateinit var locationOfProduct: String
    private lateinit var phoneOfProduct: String
    private lateinit var userPhoneNumber:String
    private var orderId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product_details)

        nameOfProduct= intent.getStringExtra("name").toString()
        priceOfProduct= intent.getStringExtra("price").toString()
        descriptionOfProduct= intent.getStringExtra("description").toString()
        genreOfProduct= intent.getStringExtra("genre").toString()
        locationOfProduct= intent.getStringExtra("location").toString()
        areaOfProduct= intent.getStringExtra("area").toString()
        phoneOfProduct= intent.getStringExtra("number").toString()
        orderId=intent.getIntExtra("orderId",0)

        tvUpdatedNameOfBook.setText(nameOfProduct)
        tvUpdatedDescOfBook.setText(descriptionOfProduct)
        tvUpdatedGenre.setText(genreOfProduct)
        tvUpdatedBookStateLocation.setText(locationOfProduct)
        tvUpdatedAreaOfBook.setText(areaOfProduct)
        tvUpdatedBookPrice.setText(priceOfProduct)

        preference=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        userPhoneNumber= preference.getString("PHONE_NUMBER","").toString()
        //toaster(userPhoneNumber)

        if (priceOfProduct!="0"){
            updatedRadioSellBtn.isChecked=true
            updateBookPrice.visibility=View.VISIBLE
        }else{
            updatedRadioDonateBtn.isChecked=true
        }

        updatedRadioDonateBtn.setOnClickListener {
            updateBookPrice.visibility=View.GONE
        }

        updatedRadioSellBtn.setOnClickListener {
            updateBookPrice.visibility=View.VISIBLE
        }

        updateAdBtn.setOnClickListener {
            updateMyAd(it)
        }

        editProductBackBtn.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun updateMyAd(it:View){
        val name=tvUpdatedNameOfBook.text.toString()
        val desc=tvUpdatedDescOfBook.text.toString()
        val genre=tvUpdatedGenre.text.toString()
        val location=tvUpdatedBookStateLocation.text.toString()
        val area=tvUpdatedAreaOfBook.text.toString()
        var price=tvUpdatedBookPrice.text.toString()
        if (updatedRadioDonateBtn.isChecked){
            price="0"
        }

        if (name.isNotEmpty() && desc.isNotEmpty()
            && genre.isNotEmpty() && location.isNotEmpty() && area.isNotEmpty()){

            when {
                name.length>50 -> {
                    errorSnackBar(it,"Book name must be less than 50.")
                    return
                }
                desc.length>150 -> {
                    errorSnackBar(it,"Description must be less than 150.")
                    return

                }
                else -> {
                    RetrofitClient.instance.updateMyAds(name,desc,genre,
                        location,area,price,orderId).enqueue(object :Callback<DefaultResponse>{
                        override fun onResponse(
                            call: Call<DefaultResponse>,
                            response: Response<DefaultResponse>
                        ) {
                            val msg=response.body()?.message.toString()
                            snackBar(it,msg)

                        }

                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            errorSnackBar(it,"Check your internet connection")

                        }

                    })
                }
            }
        }else{
            errorSnackBar(it,"Fill all fields")
        }

    }

}