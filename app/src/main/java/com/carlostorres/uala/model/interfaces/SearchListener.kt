package com.carlostorres.uala.model.interfaces

import com.carlostorres.uala.data.network.model.Search.Meal

interface SearchListener {
    fun onShowProgress()

    fun onHideProgress()

    fun onSuccess(meals: List<Meal>)

    fun onFailure(errorCode: Int)

    fun itemClicked(item: Meal, position: Int)
}