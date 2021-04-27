package com.codejunction.bookandaar.network

import com.codejunction.bookandaar.models.SignUpModel
import com.codejunction.bookandaar.repo.*
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
        @Field("fullName") userName:String,
        @Field("phoneNumber") phone:String,
        @Field("bookName") bookName:String,
        @Field("bookDesc") bookDesc:String,
        @Field("bookGenre") bookGenre:String,
        @Field("bookLocation") bookLocation:String,
        @Field("bookArea") bookArea:String,
        @Field("bookPrice") bookPrice:String,
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("/showMyAds")
    fun showMyAds(
        @Field("phoneNumber") phone: String
    ):Call<MyAdsResponse>

    @FormUrlEncoded
    @POST("/updateMyAds")
    fun updateMyAds(
        @Field("bookName") bookName:String,
        @Field("bookDesc") bookDesc:String,
        @Field("bookGenre") bookGenre:String,
        @Field("bookLocation") bookLocation:String,
        @Field("bookArea") bookArea:String,
        @Field("bookPrice") bookPrice:String,
        @Field("orderId") orderId:Int,
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("/deleteMyAds")
    fun deleteMyAds(
        @Field("phoneNumber") phone: String,
        @Field("orderId") orderId: Int
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("/books/updateUserProfile")
    fun updateUserProfile(
        @Field("email") email: String,
        @Field("area") area:String,
        @Field("phoneNumber") phone: String
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("/profileInfo")
    fun getProfileInfo(
        @Field("phoneNumber") phone: String
    ):Call<ProfileInfo>

    @POST("/allAds")
    fun getAllAds():Call<AllAdsResponse>


//    @GET("/v1/latest-news?language=it&apiKey=7goxs3CKQUAJRGhquf6BovIkIToi_vj5Uii2lTrQCVE9EtY2")
//    fun getNews() : Call<DefaultResponse>


}