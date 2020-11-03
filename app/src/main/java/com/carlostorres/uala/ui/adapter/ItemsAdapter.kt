package com.carlostorres.uala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlostorres.uala.R
import com.carlostorres.uala.data.network.model.Search.Meal
import com.carlostorres.uala.model.interfaces.SearchListener

class ItemsAdapter(val itemList: List<Meal>, val clickListener: SearchListener)
    : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsAdapter.ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemsViewHolder( inflater.inflate(R.layout.rv_items, parent, false ))
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ItemsViewHolder, position: Int) {
        holder.initialize(itemList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    public class ItemsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtTitle    = view.findViewById<TextView>(R.id.tv_title)
        val txtCategory = view.findViewById<TextView>(R.id.tv_category)
        val imgItem     = view.findViewById<ImageView>(R.id.iv_poster)

        fun initialize(item: Meal, action: SearchListener) {
            txtTitle.text    = item.strMeal
            txtCategory.text = item.strCategory

            Glide.with(imgItem.context)
                .load(item.strMealThumb)
                .into(imgItem)

            itemView.setOnClickListener {
                action.itemClicked(item, adapterPosition)
            }

        }
    }

}