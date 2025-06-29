package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.example.myapplication.product.presentation.navigation.AppNavGraph
import com.example.myapplication.product.presentation.ProductViewModel  // Adjust to your ViewModel package
//import com.example.myapplication.navigation.AppNavGraph      // Adjust to your navigation graph package

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ProductViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavGraph(viewModel)
            }
        }
    }
}
