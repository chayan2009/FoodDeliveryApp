package com.withjetpack.food.ui.theme.presentation

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.withjetpack.food.R
import com.withjetpack.food.model.RestaurantItem
import com.withjetpack.food.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailsScreen(
    restaurantId: Int,
    restaurantViewModel: RestaurantViewModel,
    onNavigateBack: () -> Unit
) {
    val restaurant = restaurantViewModel.restaurantItems.value?.find { it.id == restaurantId }
        ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Restaurant Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.mipmap.ic_default),  // URL string in restaurant.imageUrl
                contentDescription = restaurant.name,
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = restaurant.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Price: $${restaurant.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Button(
                onClick = { /* Handle order or reservation */ },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Order Now")
            }
        }
    }
}

// Mock data directly in the Preview function
@Preview
@Composable
fun PreviewRestaurantDetailsScreen() {
    val viewModel = RestaurantViewModel(Application()).apply {
        setMockData(
            listOf(
                RestaurantItem(
                    id = 1,
                    name = "Pasta Carbonara",
                    description = "Creamy pasta with pancetta",
                    price = 12.99,
                    imageUrl = "https://www.w3schools.com/w3images/pasta.jpg"
                )
            )
        )
    }

    RestaurantDetailsScreen(
        restaurantId = 1,
        restaurantViewModel = viewModel,
        onNavigateBack = {} // No-op for preview
    )
}
