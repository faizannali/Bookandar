package com.codejunction.bookandartest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunction.bookandartest.R
import com.codejunction.bookandartest.data.FavouriteAdsModel
import kotlinx.android.synthetic.main.favourite_card_view.view.*

class FavouriteAdapter(private val c:Context,private val myFavouriteAdsList:ArrayList<FavouriteAdsModel>) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        fun bindItems(favouriteAdsModel: FavouriteAdsModel) {
            itemView.product_name.text=favouriteAdsModel.nameOfProduct
            itemView.product_price.text=favouriteAdsModel.priceOfProduct
            itemView.product_location.text=favouriteAdsModel.locationOfProduct
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.favourite_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(myFavouriteAdsList[position])
    }

    override fun getItemCount(): Int {
        return myFavouriteAdsList.size
    }
}