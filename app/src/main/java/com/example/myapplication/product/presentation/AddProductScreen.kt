package com.example.myapplication.product.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.product.domain.Product
import android.widget.Toast // For Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
@Composable
fun AddProductScreen(navController: NavHostController, viewModel: ProductViewModel) {
    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    val context = LocalContext.current
   Box {
       Column(modifier = Modifier.padding(16.dp)) {
           OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
           OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
           OutlinedTextField(
               value = description,
               onValueChange = { description = it },
               label = { Text("Description") })
           OutlinedTextField(
               value = imageUrl,
               onValueChange = { imageUrl = it },
               label = { Text("Image URL") })


       }
       Button(onClick = {
           if (title.isBlank() || price.toDoubleOrNull() == null || description.isBlank() || imageUrl.isBlank()) {
               Toast.makeText(context, "يرجى تعبئة جميع الحقول بشكل صحيح", Toast.LENGTH_SHORT)
                   .show()
               return@Button
           }

           val newProduct = Product(
               title = title,
               price = price.toDouble(),
               description = description,
               image = imageUrl
           )
           viewModel.addProduct(newProduct)
           navController.popBackStack()
       }, modifier = Modifier
           .padding(16.dp)
           .align(Alignment.BottomEnd) ){
           Text("إضافة المنتج")
       }
   }
}