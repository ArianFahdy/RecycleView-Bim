package com.ilhamfidatama.androidwithkotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion.COLUMN_IMAGE
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion.COLUMN_NAME
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion.COLUMN_POPULARITY
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion.TABLE_NAME
import com.ilhamfidatama.androidwithkotlin.db.DatabaseContract.Companion._ID

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        const val DATABASE_NAME = "db_movies"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable="""
            CREATE TABLE $TABLE_NAME 
            ($_ID INTEGER PRIMARY KEY,
            $COLUMN_NAME TEXT,
            $COLUMN_POPULARITY DOUBLE,
            $COLUMN_IMAGE TEXT)"""

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


}