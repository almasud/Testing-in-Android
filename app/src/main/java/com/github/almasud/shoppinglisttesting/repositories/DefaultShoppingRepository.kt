package com.github.almasud.shoppinglisttesting.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.github.almasud.shoppinglisttesting.data.local.ShoppingDao
import com.github.almasud.shoppinglisttesting.data.local.ShoppingItem
import com.github.almasud.shoppinglisttesting.data.remote.PixabayAPI
import com.github.almasud.shoppinglisttesting.data.remote.responses.ImageResponse
import com.github.almasud.shoppinglisttesting.other.Resource
import timber.log.Timber
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch(e: Exception) {
            Timber.e(e, "EXCEPTION:")
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}














