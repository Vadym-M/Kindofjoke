package com.vinade.kindofjoke.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.vinade.kindofjoke.MainActivity
import com.vinade.kindofjoke.R

class CategoryAdapter(private val data: ArrayList<String>, private val activity: MainActivity): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var list = arrayListOf<String>()
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val chip: Chip = view.findViewById(R.id.category_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
    holder.chip.text = item
        holder.chip.setOnCheckedChangeListener { compoundButton, b ->
            if(b){
                list.add(compoundButton.text.toString())
            }else{
                list.remove(compoundButton.text.toString())
            }

            activity.observe(getCategotyString())
        }

    }

    override fun getItemCount() = data.size

    fun getCategotyString(): String{
        var regenerateCategory = ""
        if(list.count() != 0){
            for (i in list){
                if (regenerateCategory == ""){
                    regenerateCategory = i
                }else{
                    regenerateCategory = regenerateCategory +","+ i
                }

            }
        }else{
            regenerateCategory = "Any"
        }
        return  regenerateCategory
    }
}