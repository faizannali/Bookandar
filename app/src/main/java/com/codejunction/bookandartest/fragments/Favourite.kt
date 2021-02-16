package com.codejunction.bookandartest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codejunction.bookandartest.R
import com.codejunction.bookandartest.adapters.FavouriteAdapter
import com.codejunction.bookandartest.data.FavouriteAdsModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favourite.*


class Favourite : Fragment() {
    var navController:NavController?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)

        favouriteBackBtn.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }

        val defaultAdList=ArrayList<FavouriteAdsModel>()
        defaultAdList.add(FavouriteAdsModel("My new notepad", "100","Uttam Nagar"))
        defaultAdList.add(FavouriteAdsModel("My new Book", "200","Janakpuri"))
        defaultAdList.add(FavouriteAdsModel("My Book", "300","Palam Vihar"))
        defaultAdList.add(FavouriteAdsModel("My new Novel", "50000","Rajori Garden"))
        defaultAdList.add(FavouriteAdsModel("My new notepad", "100","Uttam Nagar"))
        defaultAdList.add(FavouriteAdsModel("My new Book", "200","Janakpuri"))
        defaultAdList.add(FavouriteAdsModel("My Book", "300","Palam Vihar"))
        defaultAdList.add(FavouriteAdsModel("My new Novel", "50000","Rajori Garden"))


        favouriteAdsRecycler.layoutManager=LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        val favAdapter=FavouriteAdapter(requireContext(), defaultAdList)
        favouriteAdsRecycler.adapter=favAdapter


    }


}