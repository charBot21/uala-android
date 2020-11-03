package com.carlostorres.uala.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlostorres.uala.R
import com.carlostorres.uala.data.network.model.Search.Meal
import com.carlostorres.uala.databinding.ActivitySearchBinding
import com.carlostorres.uala.model.interfaces.SearchListener
import com.carlostorres.uala.ui.adapter.ItemsAdapter
import com.carlostorres.uala.ui.utils.general.hide
import com.carlostorres.uala.ui.utils.general.show
import com.carlostorres.uala.ui.utils.general.toast
import com.carlostorres.uala.ui.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchListener {

    private lateinit var viewModel     : SearchViewModel
    private lateinit var binding       : ActivitySearchBinding
    private lateinit var recyclerView  : RecyclerView
    private lateinit var layoutManager : LinearLayoutManager
    private lateinit var adapter       : ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.searchvm = viewModel
        viewModel.searchListener = this

        recyclerView = findViewById(R.id.rv_list_items)
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onShowProgress() {
        search_progress.show()
    }

    override fun onHideProgress() {
        search_progress.hide()
    }

    override fun onSuccess(meals: List<Meal>) {
        recyclerView.layoutManager = layoutManager
        adapter = ItemsAdapter(meals, this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onFailure(errorCode: Int) {
        val errorMessageConnection = getString(R.string.error_connection)
        val emptyField = getString(R.string.empty_field)

        search_progress.hide()

        if ( errorCode == 1 ) {
            toast(emptyField)
        } else if ( errorCode == 2 ) {
            toast(errorMessageConnection)
        }
    }

    override fun itemClicked(item: Meal, position: Int) {
        val itemIntent = Intent(this, ItemDetailActivity::class.java)
        itemIntent.putExtra("linkVideo", item.strYoutube)
        startActivity(itemIntent)
    }
}