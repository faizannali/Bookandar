package com.codejunction.bookandaar.network

import com.codejunction.bookandaar.models.HomeProductModel
import com.codejunction.bookandaar.models.LoginResponse
import com.codejunction.bookandaar.models.MyAdsModel
import com.codejunction.bookandaar.models.SignUpModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("/signUp")
    fun signUp(
        @Field("fullName") name: String,
        @Field("email") email: String,
        @Field("location") location: String,
        @Field("phoneNumber") phone: String,
        @Field("password") password: String,
    ):Call<SignUpModel>

    @FormUrlEncoded
    @POST("/login/userCheck")
    fun login(
        @Field("phoneNumber") phone: String,
        @Field("password") password: String
    ):Call<LoginResponse>

    @FormUrlEncoded
    @POST("/postMyAds")
    fun postMyAds(
        @Field("phoneNumber") phone:String,
        @Field("bookName") bookName:String,
        @Field("bookDesc") bookDesc:String,
        @Field("bookGenre") bookGenre:String,
        @Field("bookLocation") bookLocation:String,
        @Field("bookArea") bookArea:String,
        @Field("bookPrice") bookPrice:String,
    ):Call<LoginResponse>


//    @GET("/v1/latest-news?language=it&apiKey=7goxs3CKQUAJRGhquf6BovIkIToi_vj5Uii2lTrQCVE9EtY2")
//    fun getNews() : Call<LoginResponse>

    @GET("/fetch/bookDetails")
    fun getNews() : Call<HomeProductModel>

    @GET("/fetch/bookDetails")
    fun showDetails():Call<MyAdsModel>

}