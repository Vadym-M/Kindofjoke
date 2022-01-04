package com.vinade.kindofjoke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinade.kindofjoke.model.Joke
import com.vinade.kindofjoke.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<Joke>> = MutableLiveData()

    fun getJoke(){
        viewModelScope.launch {
          val response: Response<Joke> = repository.getJoke()
            myResponse.value = response
        }
    }
}