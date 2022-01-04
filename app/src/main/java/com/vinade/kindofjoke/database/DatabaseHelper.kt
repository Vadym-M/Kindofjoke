package com.vinade.kindofjoke.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.vinade.kindofjoke.model.Flags
import com.vinade.kindofjoke.model.JokeX

val DATABASENAME = "FAVORITE JOKES"
val TABLENAME = "Jokes"
val COL_CATEGORY = "category"
val COL_DELIVERY = "delivery"
val COL_JOKE = "joke"
val COL_SETUP = "setup"
val COL_ID = "id"

class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY," + COL_CATEGORY + " VARCHAR(256)," + COL_DELIVERY + " VARCHAR(256)," + COL_JOKE + " VARCHAR(256)," + COL_SETUP + " VARCHAR(256))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }
    fun insertData(joke: JokeX) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, joke.id)
        contentValues.put(COL_CATEGORY, joke.category)
        contentValues.put(COL_DELIVERY, joke.delivery)
        contentValues.put(COL_JOKE, joke.joke)
        contentValues.put(COL_SETUP, joke.setup)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        database.close()
    }
    @SuppressLint("Range")
    fun readData(): MutableList<JokeX> {
        val list: MutableList<JokeX> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var jokeX : JokeX? = null
                val id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                val category = result.getString(result.getColumnIndex(COL_CATEGORY))
                val joke = result.getString(result.getColumnIndex(COL_JOKE))
                if (joke != null){
                    jokeX = JokeX(category, "", Flags(false,false,false,false,false,false), id, joke, "", false, "", "")
                }else{
                    val setup = result.getString(result.getColumnIndex(COL_SETUP))
                    var delivery = result.getString(result.getColumnIndex(COL_DELIVERY))
                    jokeX = JokeX(category, delivery, Flags(false,false,false,false,false,false), id, "", "", false, setup, "")
                }


                list.add(jokeX!!)
            }
            while (result.moveToNext())
        }
        return list
    }
    fun deleteData(id: Int){
        val db = this.writableDatabase
        db.delete(TABLENAME, "id=?", arrayOf(id.toString()))
        db.close()
    }
}