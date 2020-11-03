package com.carlostorres.uala.data.repository

import com.carlostorres.uala.data.network.model.ItemsApi
import com.carlostorres.uala.data.network.model.Search.ItemsResponse
import retrofit2.Response

class SearchRepository {

    suspend fun getItems( searchString: String ): Response<ItemsResponse> {
        return ItemsApi().getItems(searchString)
    }
}