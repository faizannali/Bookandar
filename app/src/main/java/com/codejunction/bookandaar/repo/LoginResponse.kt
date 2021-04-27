package com.codejunction.bookandaar.repo

data class LoginResponse(
    val fullName: String,
    val password: String,
    val phoneNumber: String
)