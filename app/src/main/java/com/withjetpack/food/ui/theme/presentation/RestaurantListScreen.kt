package com.withjetpack.food.ui.theme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.withjetpack.food.model.RestaurantItem
import com.withjetpack.food.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantListScreen(
    navController: NavHostController,
    restaurantViewModel: RestaurantViewModel
) {
    val restaurantItems = restaurantViewModel.restaurantItems.value ?: emptyList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Restaurants") },
                actions = {
                    // You can add any action items here
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(restaurantItems) { item ->
                RestaurantItemView(item) {
                    navController.navigate("restaurant_details/${item.id}")
                }
            }
        }
    }
}

@Composable
fun RestaurantItemView(restaurant: RestaurantItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Load the image (you can replace this with a real URL)
            Image(
                painter = rememberAsyncImagePainter("file:///android_asset/${restaurant.imageUrl}"),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = restaurant.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "$${restaurant.price}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
fun RestaurantItemPreview() {
    RestaurantItemView(
        restaurant = RestaurantItem(1, "Pasta Carbonara", "Creamy pasta with pancetta", 12.99, "https://www.w3schools.com/w3images/pasta.jpg"),
        onClick = {}
    )
}