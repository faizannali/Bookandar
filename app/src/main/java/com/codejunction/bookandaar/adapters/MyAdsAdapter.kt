package com.codejunction.bookandaar.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.models.MyAdsModel
import com.codejunction.bookandaar.ui.EditProductDetails
import kotlinx.android.synthetic.main.my_ads_card_view.view.*


class MyAdsAdapter(private val c: Context, private val myAdsList:ArrayList<MyAdsModel>) :
    RecyclerView.Adapter<MyAdsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItems(myAdsModel: MyAdsModel) {
            val nameOfProduct=myAdsModel.myNameOfProduct
            val priceOfProduct=myAdsModel.myPriceOfProduct
            val locationOfProduct=myAdsModel.myLocationOfProduct
            val viewsOfProduct=myAdsModel.myViewOnProduct

            itemView.myAds_product_name.text=nameOfProduct
            itemView.myAds_product_price.text=priceOfProduct
            itemView.myAds_product_location.text=locationOfProduct
            itemView.myAdsViews.text=viewsOfProduct

            itemView.editProductBtn.setOnClickListener {
                val intent=Intent(itemView.context,EditProductDetails::class.java)
                intent.putExtra("name",nameOfProduct)
                intent.putExtra("price",priceOfProduct)
                intent.putExtra("location",locationOfProduct)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.my_ads_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(myAdsList[position])
    }

    override fun getItemCount(): Int {
        return myAdsList.size
    }
}