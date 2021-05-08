package com.ilhamfidatama.androidwithkotlin.db

import android.provider.BaseColumns

class DatabaseContract : BaseColumns {

    companion object {
        const val TABLE_NAME = "tbl_movies"

        const val _ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_POPULARITY = "popularity"
        const val COLUMN_IMAGE = "image"
    }
}