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
import com.codejunction.bookandaar.repo.Info
import com.codejunction.bookandaar.ui.ViewProduct
import kotlinx.android.synthetic.main.product_card_layout.view.*


class HomeAdapterProduct(private val ctx:Context, private val dataList: ArrayList<Info>): RecyclerView.Adapter<HomeAdapterProduct.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItems(homeProductModel: Info) {
           itemView.home_book_name.text=homeProductModel.bookName
           itemView.home_book_price.text= homeProductModel.bookPrice.toString()
           itemView.home_book_location.text=homeProductModel.bookArea

            itemView.setOnClickListener {
                val name=itemView.home_book_name.text.toString()
                val description= homeProductModel.bookDesc
                val price=itemView.home_book_price.text.toString()
                val area=homeProductModel.bookArea
                val genre=homeProductModel.bookGenre
                val number=homeProductModel.phoneNumber
                val orderId=homeProductModel.orderId
                val fullName=homeProductModel.fullName
                val imageContainer=itemView.findViewById<View>(R.id.book_image)

//                Toast.makeText(itemView.context,name+price+loc,Toast.LENGTH_SHORT).show()
                val intent= Intent(itemView.context,ViewProduct::class.java)
                val options=ActivityOptions.makeSceneTransitionAnimation(itemView.context as Activity,imageContainer,"robot")
                intent.putExtra("BookName",name)
                intent.putExtra("BookDescription",description)
                intent.putExtra("BookPrice",price)
                intent.putExtra("BookArea",area)
                intent.putExtra("BookGenre",genre)
                intent.putExtra("BookOwnerNumber",number)
                intent.putExtra("FullName",fullName)
                intent.putExtra("OrderId",orderId)
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