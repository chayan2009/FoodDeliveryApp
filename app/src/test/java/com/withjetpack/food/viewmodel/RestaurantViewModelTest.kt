package com.withjetpack.food.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.withjetpack.food.model.RestaurantItem
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

class RestaurantViewModelTest {

    @get:Rule
    val instantTaskExecutorRule=InstantTaskExecutorRule()

    private lateinit var viewModel: RestaurantViewModel
    private lateinit var application: Application

    @Before
    fun setUp() {
        application=Mockito.mock(Application::class.java)
        viewModel= RestaurantViewModel(application)
    }

    @Test
    fun testSetMockData() {
        val mockData = listOf(
            RestaurantItem(1, "Test Restaurant", "Test description", 9.99, "test_image.jpg")
        )
        viewModel.setMockData(mockData)
        assertEquals(mockData, viewModel.restaurantItems.value)
    }


    @Test
    fun getRestaurantItems() {
    }

    @Test
    fun setMockData() {
    }
}