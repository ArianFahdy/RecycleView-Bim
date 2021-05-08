package com.ilhamfidatama.androidwithkotlin.db

import android.content.ContentValues
import android.content.Context
import com.ilhamfidatama.androidwithkotlin.Movie
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion.COLUMN_IMAGE
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion.COLUMN_NAME
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion.COLUMN_POPULARITY
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion.TABLE_NAME
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion._ID

class DatabaseLocalManager(private val context: Context) {

    private val dbHelper by lazy {
        DatabaseHelper(context)
    }

    private val writeadbleDb = dbHelper.writableDatabase
    private val readableDb = dbHelper.readableDatabase

    fun getMovies() : MutableList<Movie>{
        val movies = mutableListOf<Movie>()

        readableDb.query(
                false,
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        )?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val popularity = getDouble(getColumnIndexOrThrow(COLUMN_POPULARITY))
                val image = getString(getColumnIndexOrThrow(COLUMN_IMAGE))

                movies.add(Movie(id, name, popularity, image))
            }
        }
        return movies
    }

    fun insert(name : String, popularity : String, image : String){

        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_POPULARITY, popularity)
        values.put(COLUMN_IMAGE, image)

        writeadbleDb.insert(
                TABLE_NAME,
                null,
                values
        )

    }

    fun delete(name : String){
        val selection = COLUMN_NAME + " LIKE ?"
        val selectionArgs = arrayOf(name)

        writeadbleDb.delete(
                TABLE_NAME,
                selection,
                selectionArgs
        )
    }

}