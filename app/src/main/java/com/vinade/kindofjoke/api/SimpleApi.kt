package com.vinade.kindofjoke.api

import com.vinade.kindofjoke.model.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    @GET("joke/{category}?amount=10")
    suspend fun getJoke(@Path("category") category: String): Response<Joke>
}