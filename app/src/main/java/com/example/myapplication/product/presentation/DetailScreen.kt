package com.example.myapplication.product.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun DetailScreen(viewModel: ProductViewModel) {
    val product = viewModel.selectedProduct

    product?.let {
        Column(modifier = Modifier.padding(16.dp).verticalScroll(enabled = true, reverseScrolling = false, state = rememberScrollState(), flingBehavior = null)) {
            AsyncImage(model = it.image, contentDescription = it.title)
            Spacer(modifier = Modifier.height(16.dp))
            Text(it.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("${it.price} $", color = Color.Green)
            Text(it.description)
        }
    }
}
