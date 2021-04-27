package com.codejunction.bookandaar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codejunction.bookandaar.BaseActivity
import com.codejunction.bookandaar.R
import kotlinx.android.synthetic.main.activity_view_product.*

class ViewProduct : BaseActivity() {
    private lateinit var nameOfProduct: String
    private lateinit var descriptionOfProduct: String
    private lateinit var genreOfProduct: String
    private lateinit var areaOfProduct: String
    private lateinit var priceOfProduct: String
    private lateinit var fullName: String
    private lateinit var userPhoneNumber:String
    private var orderId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)

        nameOfProduct= intent.getStringExtra("BookName").toString()
        priceOfProduct= intent.getStringExtra("BookPrice").toString()
        descriptionOfProduct= intent.getStringExtra("BookDescription").toString()
        genreOfProduct= intent.getStringExtra("BookGenre").toString()
        areaOfProduct= intent.getStringExtra("BookArea").toString()
        userPhoneNumber= intent.getStringExtra("BookOwnerNumber").toString()
        fullName=intent.getStringExtra("FullName").toString()
        orderId=intent.getIntExtra("OrderId",0)

        viewProductName.text=nameOfProduct
        viewProductDescription.text=descriptionOfProduct
        viewProductLocation.text=areaOfProduct
        viewProductGenre.text=genreOfProduct
        userNameDetails.text="Ad posted by $fullName"

        if (priceOfProduct=="0"){
            viewProductPrice.text="Free(Donation)"
        }else{
            viewProductPrice.text="₹ $priceOfProduct"
        }

        //call button pr Dialer attach krna hai

    }
}