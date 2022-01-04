package com.vinade.kindofjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinade.kindofjoke.adapter.CategoryAdapter
import com.vinade.kindofjoke.repository.Repository
import com.yusufpats.backdroplayout.BackdropLayout

class MainActivity : AppCompatActivity() {
    private lateinit var  viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getJoke()
        viewModel.myResponse.observe(this, Observer { response ->
            //Log.e("Response", response.delivery)
        })
        val backdropLayout = findViewById<BackdropLayout>(R.id.backdrop_layout)
        val frontLayer = findViewById<CardView>(R.id.front_layer)
        val recycler = findViewById<RecyclerView>(R.id.recycler_filter)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        backdropLayout.frontSheet = frontLayer
        val categories = arrayListOf<String>()
        categories.add("Programming")
        categories.add("Misc")
        categories.add("Dark")
        categories.add("Pun")
        categories.add("Spooky")
        categories.add("Christmas")
        recycler.adapter = CategoryAdapter(categories)

// The triggerView can be any view that should trigger the backdrop toggle()
// Toggle backdrop on trigger view click
        val triggerView = findViewById<Button>(R.id.button_backdrop)
        backdropLayout.duration = 800
        backdropLayout.revealHeight = 55
        triggerView.setOnClickListener {
            backdropLayout.toggleBackdrop()
        }
    }
}