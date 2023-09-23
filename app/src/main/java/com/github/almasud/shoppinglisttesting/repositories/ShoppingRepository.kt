package com.github.almasud.shoppinglisttesting.repositories

import androidx.lifecycle.LiveData
import com.github.almasud.shoppinglisttesting.data.local.ShoppingItem
import com.github.almasud.shoppinglisttesting.data.remote.responses.ImageResponse
import com.github.almasud.shoppinglisttesting.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}