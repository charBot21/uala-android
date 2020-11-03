package com.carlostorres.uala.data.network.model

import com.carlostorres.uala.data.network.model.Search.ItemsResponse
import com.carlostorres.uala.ui.utils.constants.Environment
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsApi {

    @GET(Environment.SEARCH)
    suspend fun getItems(
        @Query("s") searchString: String
    ): Response<ItemsResponse>

    companion object {
        operator fun invoke(): ItemsApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Environment.URL_BASE)
                .build()
                .create(ItemsApi::class.java)
        }
    }
}