package com.example.myapplication.product.data

import com.example.myapplication.ShopApplication
import com.example.myapplication.product.data.local.AppDatabase
import com.example.myapplication.product.domain.Product
import com.example.myapplication.product.data.remote.RetrofitInstance
import com.example.myapplication.fromProductToLocalProduct

class ProductRepository {
    private val api = RetrofitInstance.api
    private val dao =
        AppDatabase.getDataBaseInstance(context = ShopApplication.getApplicationContext())

    suspend fun getProducts(): List<Product> {
        return try {
            api.getProducts().map { item ->
                Product(
                    item.id,
                    item.title,
                    item.price,
                    item.description,
                    item.image
                )
            }
        } catch (e: Exception) {
            emptyList() // handle errors appropriately
        }
    }

    suspend fun insertProduct(product: Product) {
        dao.insertProduct(product = product.fromProductToLocalProduct())
    }

    suspend fun getAllProducts(): List<Product> {
        return dao.getAllProducts().map { item ->
            Product(
                item.id,
                item.title,
                item.price,
                item.description,
                item.image
            )
        }
    }
}
