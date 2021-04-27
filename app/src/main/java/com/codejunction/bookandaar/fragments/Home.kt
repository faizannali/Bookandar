package com.codejunction.bookandaar.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.adapters.CategoriesAdapter
import com.codejunction.bookandaar.adapters.HomeAdapterProduct
import com.codejunction.bookandaar.models.CategoriesModel
import com.codejunction.bookandaar.models.HomeProductModel
import com.codejunction.bookandaar.network.RetrofitClient
import com.codejunction.bookandaar.repo.AllAdsResponse
import com.codejunction.bookandaar.repo.Info
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home : Fragment() {
    lateinit var filteredList: ArrayList<Info>
    lateinit var myList: ArrayList<Info>
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

        //this code is for book categories
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

        getData()

        searchBar?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filteredList = ArrayList()
                if (p0.toString() != "") {
                    for (item in myList) {
                        if (item.bookName.toLowerCase().contains(p0.toString().toLowerCase())) {
                            filteredList.add(item)
                        }
                    }
                    setupRecyclerView(filteredList)
                } else {
                    setupRecyclerView(myList)
                }
            }

        })



    }
    private fun getData(){
        myList=ArrayList()
        RetrofitClient.instance.getAllAds().enqueue(object : Callback<AllAdsResponse> {
            override fun onResponse(
                call: Call<AllAdsResponse>,
                response: Response<AllAdsResponse>
            ) {
                val bookList = response.body()?.info
                myList=bookList!!

                adapter = HomeAdapterProduct(requireContext(), bookList!!)
                //setting up layout manager to recyclerView
                productRecycler.layoutManager= GridLayoutManager(
                    requireContext(),2,
                    LinearLayoutManager.VERTICAL,
                    false
                )

                //setting adapter to recyclerView
                productRecycler.adapter=adapter
            }

            override fun onFailure(call: Call<AllAdsResponse>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Check your Internet Connection",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })


    }


    private fun setupRecyclerView(list: ArrayList<Info>) {

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




}