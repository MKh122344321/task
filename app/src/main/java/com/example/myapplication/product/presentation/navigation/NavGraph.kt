package com.example.myapplication.product.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.product.presentation.AddProductScreen
import com.example.myapplication.product.presentation.DetailScreen
import com.example.myapplication.product.presentation.HomeScreen
import com.example.myapplication.product.presentation.ProductViewModel

@Composable
fun AppNavGraph(viewModel: ProductViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, viewModel) }
        composable("details") { DetailScreen(viewModel) }
        composable("add") { AddProductScreen(navController, viewModel) }
    }
}
