package com.carlostorres.uala.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.carlostorres.uala.model.interfaces.DetailListener

class DetailViewModel: ViewModel() {

    var listener: DetailListener ?= null

    fun closeView(view: View) {
        listener?.close()
    }
}