package com.vinade.kindofjoke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinade.kindofjoke.model.Joke
import com.vinade.kindofjoke.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Joke> = MutableLiveData()

    fun getJoke(){
        viewModelScope.launch {
          val response: Joke = repository.getJoke()
            myResponse.value = response
        }
    }
}