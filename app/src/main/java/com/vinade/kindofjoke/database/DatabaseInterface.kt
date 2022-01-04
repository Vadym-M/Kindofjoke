package com.vinade.kindofjoke.database

import com.vinade.kindofjoke.model.JokeX

interface DatabaseInterface {
    fun writeJokeX(jokeX: JokeX)
    fun readJokeX():MutableList<JokeX>
    fun deleteJokeX(jokeX: JokeX)
    fun getIdAll():MutableList<Int>
}