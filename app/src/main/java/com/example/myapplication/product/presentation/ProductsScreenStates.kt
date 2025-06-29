package com.example.myapplication.product.presentation

import com.example.myapplication.product.domain.Product

data class ProductsScreenStates(
    val isLoading: Boolean,
    val products: List<Product>,
    val errormessage: String ?=null
)