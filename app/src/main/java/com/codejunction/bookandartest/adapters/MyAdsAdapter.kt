package com.codejunction.bookandartest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunction.bookandartest.R
import com.codejunction.bookandartest.data.MyAdsModel
import kotlinx.android.synthetic.main.my_ads_card_view.view.*


class MyAdsAdapter(private val c: Context, private val myAdsList:ArrayList<MyAdsModel>) :
    RecyclerView.Adapter<MyAdsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItems(myAdsModel: MyAdsModel) {
            itemView.myAds_product_name.text=myAdsModel.myNameOfProduct
            itemView.myAds_product_price.text=myAdsModel.myPriceOfProduct
            itemView.myAds_product_location.text=myAdsModel.myLocationOfProduct
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