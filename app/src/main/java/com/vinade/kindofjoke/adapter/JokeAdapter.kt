package com.vinade.kindofjoke.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vinade.kindofjoke.R
import com.vinade.kindofjoke.model.Joke

class JokeAdapter(private val data: Joke): RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textJoke: TextView = view.findViewById(R.id.joke_text_item)
    val categoryJoke: TextView = view.findViewById(R.id.joke_category_item)
    val likeBtnJoke: TextView = view.findViewById(R.id.joke_like_button)
    val shareBtnJoke: TextView = view.findViewById(R.id.joke_share_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.jokes[position]
        holder.textJoke.text = item.setup + "\n" + item.delivery
        holder.categoryJoke.text = item.category
        holder.likeBtnJoke.setOnClickListener {
            Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show()
        }
        holder.shareBtnJoke.setOnClickListener {
            Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = data.jokes.size
}