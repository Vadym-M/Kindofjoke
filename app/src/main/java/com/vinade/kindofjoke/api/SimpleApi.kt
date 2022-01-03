package com.vinade.kindofjoke.api

import com.vinade.kindofjoke.model.Joke
import retrofit2.http.GET

interface SimpleApi {
    @GET("joke/Any?lang=en")
    suspend fun getJoke(): Joke
}