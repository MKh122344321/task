package com.example.myapplication

import com.example.myapplication.product.data.local.LocalProduct
import com.example.myapplication.product.domain.Product

fun Product.toProduct(): Product {
    return Product(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        image = this.image
    )
}

fun Product.fromProductToLocalProduct(): LocalProduct {
    return LocalProduct(
        id = this.id, title = this.title,
        price = this.price,
        description = this.description,
        image = this.image
    )
}

fun LocalProduct.formLocatToProduct(): Product {

    return  Product( id = this.id, title = this.title,
        price = this.price,
        description = this.description,
        image = this.image)
}


