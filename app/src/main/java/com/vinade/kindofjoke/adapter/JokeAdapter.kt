package com.vinade.kindofjoke.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.vinade.kindofjoke.R
import com.vinade.kindofjoke.database.Database
import com.vinade.kindofjoke.model.Joke
import com.vinade.kindofjoke.model.JokeX

class JokeAdapter(private val data: ArrayList<JokeX>): RecyclerView.Adapter<JokeAdapter.ViewHolder>() {
    lateinit var context: Context
    lateinit var database: Database
    lateinit var idFavoriteJokesX: MutableList<Int>


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textJoke: TextView = view.findViewById(R.id.joke_text_item)
    val categoryJoke: TextView = view.findViewById(R.id.joke_category_item)
    val likeBtnJoke: MaterialButton = view.findViewById(R.id.joke_like_button)
    val shareBtnJoke: MaterialButton = view.findViewById(R.id.joke_share_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        database = Database(context)
        idFavoriteJokesX = database.getIdAll()
        val view = LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        var isFavorite = false
        holder.likeBtnJoke.icon = ContextCompat.getDrawable(context, R.drawable.ic_cards_heart_outline)
        for (i in idFavoriteJokesX){
            if (item.id.equals(i)){
                holder.likeBtnJoke.icon = ContextCompat.getDrawable(context, R.drawable.ic_cards_heart)
                isFavorite = true
            }
        }

        if(item.joke == null || item.joke == ""){
            holder.textJoke.text = item.setup + "\n" + item.delivery
        }else{
            holder.textJoke.text = item.joke
        }
        holder.categoryJoke.text = item.category
        holder.likeBtnJoke.setOnClickListener {

            if (isFavorite){
                database.deleteJokeX(item)
                updateFavorite()
                holder.likeBtnJoke.icon = ContextCompat.getDrawable(context, R.drawable.ic_cards_heart_outline)
                val index = data.indexOf(item)
                data.removeAt(index)
                notifyItemRemoved(index)
            }else{
                database.writeJokeX(item)
                updateFavorite()
                holder.likeBtnJoke.icon = ContextCompat.getDrawable(context, R.drawable.ic_cards_heart)
            }

            Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show()
        }
        holder.shareBtnJoke.setOnClickListener {
            Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = data.size

    fun updateFavorite(){
        idFavoriteJokesX = database.getIdAll()
    }

}