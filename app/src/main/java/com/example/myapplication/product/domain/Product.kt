package com.example.myapplication.product.domain


data class Product(
    val id: Int = 0,
    val title: String,
    val price: Double,
    val description: String,
    val image: String

)