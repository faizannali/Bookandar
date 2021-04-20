package com.codejunction.bookandaar.models

data class HomeProductModel(
    val book_price:String,
    val book_name:String,
    val book_location:String,
    val message: String,
    val rows: List<Row>
)
