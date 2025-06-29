package com.example.myapplication.product.domain

import com.example.myapplication.product.data.ProductRepository

class GetAllProductUseCase {
    private val repo = ProductRepository()
    suspend operator fun invoke():List<Product>{
        return repo.getProducts().sortedBy { it.price }
    }
}