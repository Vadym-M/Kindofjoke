package com.vinade.kindofjoke.api

import com.vinade.kindofjoke.model.Joke
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("joke/Any?type=twopart&amount=10")
    suspend fun getJoke(): Response<Joke>
}