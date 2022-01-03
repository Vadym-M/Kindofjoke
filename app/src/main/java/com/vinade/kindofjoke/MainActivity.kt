package com.vinade.kindofjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vinade.kindofjoke.repository.Repository

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
            Log.e("Response", response.delivery)
        })
    }
}