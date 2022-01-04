package com.vinade.kindofjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var  viewModel: MainViewModel
    private lateinit var  recyclerJoke: RecyclerView
    var responseData = arrayListOf<JokeX>()
    var jokeAdapter = JokeAdapter(responseData)
    var isFilter = false
    var isMenu = false
    var isGoing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initJokeAdapter()
        getResponse()
        initBackdrop()

        swipe_refresh.setOnRefreshListener {
            getResponse()
        }

    }
    fun getResponse(){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getJoke("Misc")
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
        triggerView.setOnClickListener {
            if(!isGoing){
            backdropLayout.revealHeight = 55
            if(!isMenu){
                isGoing = true
                recycler.visibility = View.VISIBLE
                drop_menu.visibility = View.GONE
                backdropLayout.toggleBackdrop()
                isFilter = !isFilter
                Handler().postDelayed({
                    isGoing = false
                }, 1000)
            }else{
                isGoing = true
                backdropLayout.toggleBackdrop()
                isMenu = !isMenu
                isFilter = !isFilter
                Handler().postDelayed({
                    drop_menu.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    backdropLayout.toggleBackdrop()
                    isGoing = false
                }, 1000)

            }}

        }
        button_burger.setOnClickListener {
            if(!isGoing){
            backdropLayout.revealHeight = 155
            if(!isFilter){
                isGoing = true
                backdropLayout.toggleBackdrop()
                recycler.visibility = View.GONE
                drop_menu.visibility = View.VISIBLE
                isMenu = !isMenu
                Handler().postDelayed({
                    isGoing = false
                }, 1000)
            }else{
                isGoing = true
                backdropLayout.toggleBackdrop()
                isFilter = !isFilter
                isMenu = !isMenu
                Handler().postDelayed({
                    drop_menu.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                    backdropLayout.toggleBackdrop()
                    isGoing = false
                }, 1000)
            }
        }}
    }

    fun initJokeAdapter(){
        recyclerJoke = findViewById<RecyclerView>(R.id.recycler_joke)
        recyclerJoke.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerJoke.adapter = jokeAdapter
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
        swipe_refresh.isRefreshing = false
    }
}