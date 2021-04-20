package com.codejunction.bookandaar.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.models.HomeProductModel
import com.codejunction.bookandaar.ui.ViewProduct
import kotlinx.android.synthetic.main.product_card_layout.view.*


class HomeAdapterProduct(private val ctx:Context,private val dataList:ArrayList<HomeProductModel>): RecyclerView.Adapter<HomeAdapterProduct.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItems(homeProductModel: HomeProductModel) {
           itemView.home_book_name.text=homeProductModel.book_name
           itemView.home_book_price.text=homeProductModel.book_price
           itemView.home_book_location.text=homeProductModel.book_location

            itemView.setOnClickListener {
                val name=itemView.home_book_name.text.toString()
                val price=itemView.home_book_price.text.toString()
                val loc=itemView.home_book_location.text.toString()
                val imageContainer=itemView.findViewById<View>(R.id.book_image)

//                Toast.makeText(itemView.context,name+price+loc,Toast.LENGTH_SHORT).show()
                val intent= Intent(itemView.context,ViewProduct::class.java)
                val options=ActivityOptions.makeSceneTransitionAnimation(itemView.context as Activity,imageContainer,"robot")
                itemView.context?.startActivity(intent,options.toBundle())
            }
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

//    override fun getFilter(): Filter {
//       for this you need to import filterable
//    }
}