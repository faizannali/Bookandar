package com.codejunction.bookandaar.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.codejunction.bookandaar.R
import com.codejunction.bookandaar.network.RetrofitClient
import com.codejunction.bookandaar.repo.DefaultResponse
import com.codejunction.bookandaar.repo.ProfileInfo
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Profile : Fragment() {
    var navController: NavController?=null
    lateinit var preference:SharedPreferences
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

        tvPhone.isEnabled=false
        tvLocation.isEnabled=false
        preference=requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val userNumber= preference.getString("PHONE_NUMBER"," ").toString()
        loadDetails()
        profileBackBtn.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
        val areas = arrayOf(
            "Uttam Nagar", "Dawarka", "West Delhi", "North Delhi", "South Delhi"
        )

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.list_item, areas
        )
        tvProfileArea.setAdapter(adapter)

        updateProfileBtn.setOnClickListener {
            val profEmail=tvEmail.text.toString()
            val profArea=tvProfileArea.text.toString()

            if (profEmail.isNotEmpty() && profArea.isNotEmpty()){
                RetrofitClient.instance.updateUserProfile(profEmail,profArea, userNumber).enqueue(object :
                    Callback<DefaultResponse> {
                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        val msg = response.body()?.message
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
                    }

                })
            }else{
                Toast.makeText(requireContext(), "Fill Details", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun loadDetails(){
        val userNumber= preference.getString("PHONE_NUMBER"," ").toString()
        RetrofitClient.instance.getProfileInfo(userNumber).enqueue(object : Callback<ProfileInfo> {
            override fun onResponse(call: Call<ProfileInfo>, response: Response<ProfileInfo>) {
                val name = response.body()?.fullName.toString()
                val email=response.body()?.email.toString()
                val area=response.body()?.area.toString()
                val location=response.body()?.location.toString()
                val phoneNumber=response.body()?.phoneNumber.toString()
                userName.text=name
                tvEmail.setText(email)
                tvProfileArea.setText(area)
                tvLocation.setText(location)
                tvPhone.setText(phoneNumber)

            }

            override fun onFailure(call: Call<ProfileInfo>, t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }




}