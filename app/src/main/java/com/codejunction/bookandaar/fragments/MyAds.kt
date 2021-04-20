package com.codejunction.bookandaar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.adapters.MyAdsAdapter
import com.codejunction.bookandaar.models.MyAdsModel
import kotlinx.android.synthetic.main.fragment_my_ads.*


class MyAds : Fragment() {
    var navController: NavController?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_ads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController= Navigation.findNavController(view)

        myAdsBackBtn.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }

        val defaultAdList=ArrayList<MyAdsModel>()
        defaultAdList.add(MyAdsModel("My new notepad", "Rs 100","Uttam Nagar","100"))
        defaultAdList.add(MyAdsModel("My old Science Book", "Rs 100","Tilak Nagar","50"))
        defaultAdList.add(MyAdsModel("My maths book", "Rs 1000","Uttam Nagar","100"))

        myAdsRecycler.layoutManager= LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        val myAdsAdapter= MyAdsAdapter(requireContext(), defaultAdList)
        myAdsRecycler.adapter=myAdsAdapter
    }

}