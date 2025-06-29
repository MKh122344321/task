package com.example.myapplication.product.domain

class FiltterUseCase {

     operator fun invoke(List: List<Product>, texttosearch:String):List<Product>{
     return   List.filter {  it.title.startsWith(texttosearch, ignoreCase = true) }
    }

}