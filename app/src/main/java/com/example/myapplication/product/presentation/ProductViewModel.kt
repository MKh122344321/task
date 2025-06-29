package com.example.myapplication.product.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.product.domain.AddNewProductUSeCase
import com.example.myapplication.product.domain.GetAllProductUseCase
import com.example.myapplication.product.domain.Product

import com.example.myapplication.product.data.ProductRepository
import com.example.myapplication.toProduct
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
private val featchallproductsusecase = GetAllProductUseCase()
    private  val addnewproductusecase = AddNewProductUSeCase()
   private var _productList by mutableStateOf(
        ProductsScreenStates(
            products = emptyList(), isLoading = true,
        )
    )
        private set
val prductlist :State<ProductsScreenStates>
    get() = derivedStateOf { _productList }
    var selectedProduct by mutableStateOf<Product?>(null)
        private set

//    var isLoading by mutableStateOf(false)
//        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun fetchProducts() {
        viewModelScope.launch {
//            isLoading = true
            try {
                val networkproductlist = featchallproductsusecase()

                networkproductlist
                    .map { it.toProduct() } // Convert each item to a Product entity
                    .forEach { product ->
                        repository.insertProduct(product = product)
                    }
                val recivedproduts = repository.getAllProducts()
                _productList = _productList.copy(products = recivedproduts, isLoading = false)
                errorMessage = null
            } catch (e: Exception) {
//                val errorgandle = dao.getAllProducts()
                _productList = _productList.copy(errormessage = e.toString())
            }
//            isLoading = false
        }
    }


    fun selectProduct(product: Product) {
        selectedProduct = product
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            addnewproductusecase(product) // Insert into Room
            val updatedproductlist = repository.getAllProducts()
            _productList =
                _productList.copy(products = updatedproductlist)// Refresh the list from Room
        }
    }
}
