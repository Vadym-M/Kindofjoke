package com.vinade.kindofjoke.repository

import com.vinade.kindofjoke.api.RetrofitInstance
import com.vinade.kindofjoke.model.Joke

class Repository {

    suspend fun getJoke(): Joke {
        return  RetrofitInstance.api.getJoke()
    }
}