package com.vinade.kindofjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinade.kindofjoke.adapter.CategoryAdapter
import com.vinade.kindofjoke.adapter.JokeAdapter
import com.vinade.kindofjoke.model.Joke
import com.vinade.kindofjoke.model.JokeX
import com.vinade.kindofjoke.repository.Repository
import com.yusufpats.backdroplayout.BackdropLayout

class MainActivity : AppCompatActivity() {
    private lateinit var  viewModel: MainViewModel
    private lateinit var  recyclerJoke: RecyclerView
    var responseData = arrayListOf<JokeX>()
    var jokeAdapter = JokeAdapter(responseData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerJoke = findViewById<RecyclerView>(R.id.recycler_joke)
        recyclerJoke.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerJoke.adapter = jokeAdapter
        getResponse()
        initBackdrop()

    }
    fun getResponse(){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getJoke()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.e("Response", response.body()!!.jokes.size.toString())

               fillJokeAdapter(response.body()!!)

            }else{
                Log.e("Response", response.errorBody().toString())
            }

        })
    }

    fun initBackdrop(){
        val backdropLayout = findViewById<BackdropLayout>(R.id.backdrop_layout)
        val frontLayer = findViewById<CardView>(R.id.front_layer)
        val recycler = findViewById<RecyclerView>(R.id.recycler_filter)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        backdropLayout.frontSheet = frontLayer
        val categories = fillCategoryAdapter()
        recycler.adapter = CategoryAdapter(categories)

        val triggerView = findViewById<Button>(R.id.button_backdrop)
        backdropLayout.duration = 800
        backdropLayout.revealHeight = 55
        triggerView.setOnClickListener {
            backdropLayout.toggleBackdrop()
        }
    }

    fun fillCategoryAdapter(): ArrayList<String>{
        val categories = arrayListOf<String>()
        categories.add("Programming")
        categories.add("Misc")
        categories.add("Dark")
        categories.add("Pun")
        categories.add("Spooky")
        categories.add("Christmas")
        return  categories
    }

    fun fillJokeAdapter(response: Joke){
        responseData.clear()
        responseData.addAll(response.jokes)
        jokeAdapter.notifyDataSetChanged()
    }
}