package com.vinade.kindofjoke.model

data class Joke(
    val amount: Int,
    val error: Boolean,
    val jokes: List<JokeX>
) {
    constructor() : this(0,false, arrayListOf())
}

data class JokeX(
    val category: String,
    val delivery: String,
    val flags: Flags,
    val id: Int,
    val joke: String,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String
)

data class Flags(
    val explicit: Boolean,
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)