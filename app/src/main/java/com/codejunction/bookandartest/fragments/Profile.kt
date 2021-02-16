package com.codejunction.bookandartest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.codejunction.bookandartest.R
import kotlinx.android.synthetic.main.fragment_profile.*


class Profile : Fragment() {
    var navController: NavController?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController= Navigation.findNavController(view)

        profileBackBtn.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
        val countries = arrayOf(
            "Uttam Nagar", "Dawarka", "West Delhi", "North Delhi", "South Delhi"
        )

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.list_item, countries
        )
        actv.setAdapter(adapter)


    }




}