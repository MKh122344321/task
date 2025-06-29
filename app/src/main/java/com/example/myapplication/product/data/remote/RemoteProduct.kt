package com.example.myapplication.product.data.remote

import com.google.gson.annotations.SerializedName

//package com.example.myapplication.data
//

data class RemoteProduct(
@SerializedName("id")
    val id: Int = 0,
    val title: String,
    val price: Double,
    val description: String,
    val image: String

)