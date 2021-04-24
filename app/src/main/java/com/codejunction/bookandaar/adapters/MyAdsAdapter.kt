package com.codejunction.bookandaar.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.network.RetrofitClient
import com.codejunction.bookandaar.repo.DefaultResponse
import com.codejunction.bookandaar.repo.MyAdsInfo
import com.codejunction.bookandaar.ui.EditProductDetails
import kotlinx.android.synthetic.main.my_ads_card_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var orderIds:Int?=null
lateinit var phoneNumber:String
class MyAdsAdapter(private val c: Context, private val myAdsList: ArrayList<MyAdsInfo>?) :
    RecyclerView.Adapter<MyAdsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItems(myAdsModel: MyAdsInfo) {
            val nameOfProduct=myAdsModel.bookName
            val descriptionOfProduct=myAdsModel.bookDesc
            val genreOfProduct=myAdsModel.bookGenre
            val priceOfProduct=myAdsModel.bookPrice.toString()
            val locationOfProduct=myAdsModel.bookLocation
            val areaOfProduct=myAdsModel.bookArea
            val orderId=myAdsModel.orderId
            val viewsOfProduct=45.toString()
            orderIds=myAdsModel.orderId
            phoneNumber=myAdsModel.phoneNumber


            itemView.myAds_product_name.text=nameOfProduct
            itemView.myAds_product_price.text= priceOfProduct
            itemView.myAds_product_location.text=locationOfProduct
            itemView.myAdsViews.text= viewsOfProduct


            itemView.editProductBtn.setOnClickListener {
                val intent=Intent(itemView.context, EditProductDetails::class.java)
                intent.putExtra("name", nameOfProduct)
                intent.putExtra("description", descriptionOfProduct)
                intent.putExtra("genre", genreOfProduct)
                intent.putExtra("location", locationOfProduct)
                intent.putExtra("area", areaOfProduct)
                intent.putExtra("price", priceOfProduct)
                intent.putExtra("number", phoneNumber)
                intent.putExtra("orderId", orderId)
                itemView.context.startActivity(intent)
//                (itemView.context as Activity).finish()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.my_ads_card_view,
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        myAdsList?.get(position)?.let { holder.bindItems(it) }
        val productId= orderIds
        holder.itemView.remove_product.setOnClickListener {
            Toast.makeText(holder.itemView.context, productId.toString(), Toast.LENGTH_SHORT).show()

            if (productId != null) {
                RetrofitClient.instance.deleteMyAds(phoneNumber,productId).enqueue(object :
                    Callback<DefaultResponse> {
                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        val msg = response.body()?.message
                        Toast.makeText(holder.itemView.context, msg, Toast.LENGTH_SHORT).show()
                        myAdsList?.removeAt(position)
                        notifyDataSetChanged()

                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(holder.itemView.context, t.toString(), Toast.LENGTH_SHORT).show()
                    }

                })
            }

            }
        }

    override fun getItemCount(): Int {
        return myAdsList!!.size
    }


}