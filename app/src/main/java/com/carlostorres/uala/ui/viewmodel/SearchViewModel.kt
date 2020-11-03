package com.carlostorres.uala.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.carlostorres.uala.data.repository.SearchRepository
import com.carlostorres.uala.model.interfaces.SearchListener
import com.carlostorres.uala.ui.utils.coroutines.Coroutines

class SearchViewModel: ViewModel() {

    // Read data input
    val search = ObservableField<String?>("")
    var searchListener: SearchListener ?= null


    fun onSearchValue(view: View) {

        if ( !search.get().isNullOrEmpty() ) {
            searchListener?.onShowProgress()

            Coroutines.main {
                val response = SearchRepository().getItems(search.get()!!)

                if ( response.isSuccessful ) {
                    searchListener?.onSuccess(response.body()?.meals!!)
                } else {
                    searchListener?.onFailure(2)
                }
                searchListener?.onHideProgress()
            }
        } else {
            searchListener?.onFailure(1)
            searchListener?.onHideProgress()
        }
    }
}