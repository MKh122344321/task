package com.example.myapplication.product.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myapplication.product.domain.FiltterUseCase


@Composable
fun HomeScreen(navController: NavHostController, viewModel: ProductViewModel) {
    val options = viewModel.prductlist.value
    var searchQuery by remember { mutableStateOf("") }
    val filtterusecase = FiltterUseCase()
    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    if (options.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else if (options.errormessage!=null){
        Box(modifier = Modifier. padding(16.dp), contentAlignment = Alignment.Center){
            Text("AN error occured")
        }
    }
    else {
        // استخدم Box لترتيب العناصر وتثبيت FloatingActionButton في الأسفل
        Box(modifier = Modifier.fillMaxSize()) {
            // استخدم Column لترتيب العناصر رأسياً
            Column(modifier = Modifier.fillMaxSize()) {
                // حقل البحث في الأعلى
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("ابحث عن منتج") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                // تصفية المنتجات بناءً على نص البحث

                val  filteredProducts=   filtterusecase(options.products,searchQuery,)
//                val filteredProducts = options.products.filter {
//                    it.title.startsWith(searchQuery, ignoreCase = true)
//                }

                // قائمة المنتجات التي تم تصفيتها
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f) // استخدم weight لجعل الـ LazyColumn يأخذ المساحة المتبقية
                ) {
                    items(filteredProducts) { product ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    viewModel.selectProduct(product)
                                    navController.navigate("details")
                                }
                        ) {
                            Row {
                                AsyncImage(
                                    model = product.image,
                                    contentDescription = product.title,
                                    modifier = Modifier.size(100.dp)
                                )
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(product.title, fontWeight = FontWeight.Bold)
                                    Text("${product.price} $")
                                }
                            }
                        }
                    }
                }
            }

            // زر إضافة منتج جديد في الأسفل
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd) // تأكد من محاذاة الزر في الزاوية السفلى
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    }
}
