package com.example.myapplication.product.domain

import com.example.myapplication.product.data.ProductRepository

class AddNewProductUSeCase {
    private val repo = ProductRepository()

    suspend operator  fun invoke(product: Product):Unit{
        repo.insertProduct(product)
    }


}