package com.vinade.kindofjoke.repository

import com.vinade.kindofjoke.api.RetrofitInstance
import com.vinade.kindofjoke.model.Joke
import retrofit2.Response

class Repository {

    suspend fun getJoke(category: String): Response<Joke> {
        return  RetrofitInstance.api.getJoke(category)
    }
}