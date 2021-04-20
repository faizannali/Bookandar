package com.codejunction.bookandaar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.models.CategoriesModel
import kotlinx.android.synthetic.main.category_layout.view.*

class CategoriesAdapter(
    private val c: Context,
    private val myCategoryList: ArrayList<CategoriesModel>
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItems(CategoriesModel: CategoriesModel) {
            itemView.categoryName.text=CategoriesModel.categoryName
            val currentColor:String=CategoriesModel.colorName
            //itemView.setBackgroundColor(Color.parseColor(currentColor))

            //itemView.categoriesCardView.setCardBackgroundColor(Color.parseColor(currentColor))


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(myCategoryList[position])
    }

    override fun getItemCount(): Int {
        return myCategoryList.size
    }
}