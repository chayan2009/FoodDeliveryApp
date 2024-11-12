package com.withjetpack.food.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.withjetpack.food.model.RestaurantItem
import java.io.InputStreamReader

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {

    private val _restaurantItems = MutableLiveData<List<RestaurantItem>>()
    val restaurantItems: LiveData<List<RestaurantItem>> = _restaurantItems

    init {
        loadRestaurantData()
    }

    fun setMockData(mockData: List<RestaurantItem>) {
        _restaurantItems.value = mockData
    }

    private fun loadRestaurantData() {
        try {
            val inputStream = getApplication<Application>().assets.open("restaurant_data.json")
            Log.d("RestaurantViewModel", "File opened successfully.")

            val reader = InputStreamReader(inputStream)
            val listType = object : TypeToken<List<RestaurantItem>>() {}.type
            val items: List<RestaurantItem> = Gson().fromJson(reader, listType)

            Log.d("RestaurantViewModel", "Parsed ${items.size} items.")

            _restaurantItems.postValue(items)
        } catch (e: Exception) {
            Log.e("RestaurantViewModel", "Error loading data", e)
        }
    }
}
