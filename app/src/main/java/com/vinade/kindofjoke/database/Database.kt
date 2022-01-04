package com.vinade.kindofjoke.database

import android.content.Context
import android.util.Log
import com.vinade.kindofjoke.model.JokeX

class Database(context: Context) : DatabaseInterface {
    val db = DatabaseHelper(context)

    override fun writeJokeX(jokeX: JokeX) {
        db.insertData(jokeX)
    }

    override fun readJokeX(): MutableList<JokeX> {
    val jokeX = db.readData()
        Log.e("ReadJokeX", "Size of array: " + jokeX.size)
        return  jokeX
    }

    override fun deleteJokeX(jokeX: JokeX) {
      db.deleteData(jokeX.id)
    }

    override fun getIdAll(): MutableList<Int> {
        val idAll = mutableListOf<Int>()
        for (i in db.readData()){
            idAll.add(i.id)
        }
        return  idAll
    }
}