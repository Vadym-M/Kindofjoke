package com.vinade.kindofjoke.model

data class Joke(
    val category: String,
    val delivery: String,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
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