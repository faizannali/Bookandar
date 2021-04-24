package com.codejunction.bookandaar.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.adapters.MyAdsAdapter
import com.codejunction.bookandaar.network.RetrofitClient
import com.codejunction.bookandaar.repo.MyAdsResponse
import kotlinx.android.synthetic.main.fragment_my_ads.*
import kotlinx.android.synthetic.main.my_ads_card_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyAds : Fragment() {
    var navController: NavController?=null
    lateinit var preferences: SharedPreferences
    private lateinit var userNumber:String
    lateinit var myAdsAdapter: MyAdsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //navController= view?.let { Navigation.findNavController(it) }
        preferences=requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        userNumber= preferences.getString("PHONE_NUMBER"," ").toString()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_ads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController= Navigation.findNavController(view)

        myAdsBackBtn.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }

        getMyAds()

        myAdsRefreshBtn.setOnClickListener {
            getMyAds()
        }


    }

    private fun getMyAds(){
        RetrofitClient.instance.showMyAds(userNumber).enqueue(object : Callback<MyAdsResponse> {
            override fun onResponse(call: Call<MyAdsResponse>, response: Response<MyAdsResponse>) {
                val msg=response.body()?.info

                myAdsAdapter= MyAdsAdapter(requireContext(), msg)
                myAdsRecycler.adapter=myAdsAdapter
                myAdsRecycler.layoutManager= LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }

            override fun onFailure(call: Call<MyAdsResponse>, t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

}