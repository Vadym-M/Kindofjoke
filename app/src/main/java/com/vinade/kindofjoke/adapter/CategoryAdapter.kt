package com.vinade.kindofjoke.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinade.kindofjoke.R

class CategoryAdapter(private val data: ArrayList<String>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val categoryTitle: TextView
        init {
            categoryTitle = view.findViewById(R.id.category_title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
    holder.categoryTitle.text = item
    }

    override fun getItemCount() = data.size
}