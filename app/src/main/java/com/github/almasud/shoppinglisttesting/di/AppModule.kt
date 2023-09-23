package com.github.almasud.shoppinglisttesting.di

import android.content.Context
import androidx.room.Room
import com.github.almasud.shoppinglisttesting.R
import com.github.almasud.shoppinglisttesting.data.local.ShoppingDao
import com.github.almasud.shoppinglisttesting.data.local.ShoppingItemDatabase
import com.github.almasud.shoppinglisttesting.data.remote.PixabayAPI
import com.github.almasud.shoppinglisttesting.other.Constants.BASE_URL
import com.github.almasud.shoppinglisttesting.other.Constants.DATABASE_NAME
import com.github.almasud.shoppinglisttesting.repositories.DefaultShoppingRepository
import com.github.almasud.shoppinglisttesting.repositories.ShoppingRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}

















