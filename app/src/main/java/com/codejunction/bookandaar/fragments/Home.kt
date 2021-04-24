package com.codejunction.bookandaar.fragments

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.adapters.CategoriesAdapter
import com.codejunction.bookandaar.adapters.HomeAdapterProduct
import com.codejunction.bookandaar.models.CategoriesModel
import com.codejunction.bookandaar.models.HomeProductModel
import kotlinx.android.synthetic.main.fragment_home.*


class Home : Fragment() {
    lateinit var filteredList: ArrayList<HomeProductModel>
    private lateinit var adapter: HomeAdapterProduct
    lateinit var preference: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preference= requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val name=preference.getString("PHONE_NUMBER","")
        val pass= preference.getString("LOGIN","")

//        location.text=name
//        tv_location.text=pass

        val defaultAdList=ArrayList<CategoriesModel>()
        defaultAdList.add(CategoriesModel("Novel","#1D7DEF"))
        defaultAdList.add(CategoriesModel("School","#16056B"))
        defaultAdList.add(CategoriesModel("Horror","#352E44"))
        defaultAdList.add(CategoriesModel("Comics","#5696fa"))
        defaultAdList.add(CategoriesModel("Romance","#FD9519"))
        defaultAdList.add(CategoriesModel("College","#fcf3e4"))

        categoriesRecycler.layoutManager= GridLayoutManager(
            requireContext(),4,
            LinearLayoutManager.VERTICAL,
            false
        )

        val myCategoryAdapter= CategoriesAdapter(requireContext(), defaultAdList)
        categoriesRecycler.adapter=myCategoryAdapter

        init()



    }

    private fun init() {

        var list: ArrayList<HomeProductModel> = getData()
//        list.addAll(getData())
        setupRecyclerView(list)

        searchBar?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filteredList = ArrayList()
                if (p0.toString() != "") {
                    for (item in list) {
                        if (item.book_name.toLowerCase().contains(p0.toString().toLowerCase())) {
                            filteredList.add(item)
                        }
                    }
                    setupRecyclerView(filteredList)
                } else {
                    setupRecyclerView(list)
                }
            }

        })
    }

    private fun setupRecyclerView(list: ArrayList<HomeProductModel>) {

        //initlizing adapter
        adapter = HomeAdapterProduct(requireContext(), list)

        //setting up layout manager to recyclerView
        productRecycler.layoutManager= GridLayoutManager(
            requireContext(),2,
            LinearLayoutManager.VERTICAL,
            false
        )

        //setting adapter to recyclerView
        productRecycler.adapter=adapter

    }

    private fun getData(): ArrayList<HomeProductModel> {

        var productList=ArrayList<HomeProductModel>()
//        productList.add(HomeProductModel("1000","My NCERT Book","Uttam Nagar","d"))
//        productList.add(HomeProductModel("2000","Science Book For Sale","JanakPuri"))
//        productList.add(HomeProductModel("100","BCA Books","Nawada"))
//        productList.add(HomeProductModel("10000","10th CBSE course","Rajori Garden west"))
//        productList.add(HomeProductModel("1000","My NCERT Book","Uttam Nagar"))
//        productList.add(HomeProductModel("2000","Science Book For Sale","JanakPuri"))
//        productList.add(HomeProductModel("100","BCA Books","Nawada"))
//        productList.add(HomeProductModel("10000","10th CBSE course","Rajori Garden west"))
//

        return productList

    }


}