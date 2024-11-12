package com.withjetpack.food

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.withjetpack.food.ui.theme.FoodDeliveryAppTheme
import com.withjetpack.food.ui.theme.presentation.RestaurantDetailsScreen
import com.withjetpack.food.ui.theme.presentation.RestaurantListScreen
import com.withjetpack.food.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {

    private val restaurantViewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "restaurant_list") {
                        composable("restaurant_list") {
                            RestaurantListScreen(
                                navController = navController,
                                restaurantViewModel = restaurantViewModel
                            )
                        }
                        composable("restaurant_details/{id}") { navBackStackEntry ->
                            val restaurantId = navBackStackEntry.arguments?.getString("id")?.toInt() ?: 0
                            RestaurantDetailsScreen(
                                restaurantId = restaurantId,
                                restaurantViewModel = restaurantViewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
