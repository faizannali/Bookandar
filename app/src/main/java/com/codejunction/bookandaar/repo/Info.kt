package com.codejunction.bookandaar.repo

data class Info(
    val fullName:String,
    val bookArea: String,
    val bookDesc: String,
    val bookGenre: String,
    val bookLocation: String,
    val bookName: String,
    val bookPrice: Int,
    val orderId: Int,
    val phoneNumber: String
)