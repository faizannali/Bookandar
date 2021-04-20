package com.codejunction.bookandaar.network

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

object RetrofitClient {

//    private const val BASE_URL="https://bookandaar.000webhostapp.com/"
    //private const val BASE_URL="https://api.currentsapi.services"
    private const val BASE_URL="http://192.168.100.2:3001"

    private val okHttp=OkHttpClient.Builder()

    val instance: ApiInterface by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()

        retrofit.create(ApiInterface::class.java)
    }

}