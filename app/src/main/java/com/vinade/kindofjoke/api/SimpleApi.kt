package com.vinade.kindofjoke.api

import com.vinade.kindofjoke.model.Joke
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("joke/Any?&amount=100")
    suspend fun getJoke(): Response<Joke>
}