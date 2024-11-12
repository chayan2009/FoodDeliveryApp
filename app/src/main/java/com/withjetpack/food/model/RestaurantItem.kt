package com.withjetpack.food.model

data class RestaurantItem(
    var id: Int,
    var name: String,
    var description: String,
    var price: Double,
    var imageUrl: String
)