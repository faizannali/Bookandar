package com.codejunction.bookandartest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.codejunction.bookandartest.R
import com.codejunction.bookandartest.data.HomeProductModel
import kotlinx.android.synthetic.main.product_card_layout.view.*


class HomeAdapterProduct(private val c:Context,private val dataList:ArrayList<HomeProductModel>): RecyclerView.Adapter<HomeAdapterProduct.ViewHolder>(),Filterable {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItems(homeProductModel: HomeProductModel) {
           itemView.home_book_name.text=homeProductModel.book_name
           itemView.home_book_price.text=homeProductModel.book_price
           itemView.home_book_location.text=homeProductModel.book_location
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_card_layout, parent, false)
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}