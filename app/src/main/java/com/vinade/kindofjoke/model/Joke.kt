package com.vinade.kindofjoke.model

data class Joke(
    val amount: Int,
    val error: Boolean,
    val jokes: List<JokeX>
) {
    constructor() : this(0,false, arrayListOf())
}

data class JokeX(
    var category: String,
    var delivery: String,
    val flags: Flags,
    var id: Int,
    var joke: String,
    val lang: String,
    val safe: Boolean,
    var setup: String,
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