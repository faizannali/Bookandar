package com.codejunction.bookandaar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codejunction.bookandaar.R
import kotlinx.android.synthetic.main.activity_edit_product_details.*

class EditProductDetails : AppCompatActivity() {
    private lateinit var nameOfProduct: String
    private lateinit var priceOfProduct: String
    private lateinit var locationOfProduct: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product_details)

        nameOfProduct= intent.getStringExtra("name").toString()
        priceOfProduct= intent.getStringExtra("price").toString()
        locationOfProduct= intent.getStringExtra("location").toString()

        tvUpdatedNameOfBook.setText(nameOfProduct)
        tvUpdatedAreaOfBook.setText(locationOfProduct)
        tvUpdatedBookPrice.setText(priceOfProduct)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}