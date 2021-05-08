package com.ilhamfidatama.androidwithkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ilhamfidatama.androidwithkotlin.db.DatabaseLocalManager
import kotlinx.android.synthetic.main.activity_tambah.*

class TambahActivity : AppCompatActivity() {

    private val movieManager by lazy {
        DatabaseLocalManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        btn_tambah.setOnClickListener {
            tambahMovie()

        }

    }

    private fun tambahMovie() {
        val nama = edt_name.text.toString()
        val popularity = edt_popularity.text.toString()
        val image = edt_image.text.toString()

        if (nama.isEmpty()){
            edt_name.error = "Field ini wajib diisi"
            edt_name.requestFocus()
        } else if (popularity.isEmpty()){
            edt_popularity.error = "Field ini wajib diisi"
            edt_popularity.requestFocus()
        } else if (image.isEmpty()){
            edt_image.error = "Field ini wajib diisi"
            edt_image.requestFocus()
        } else {
            movieManager.insert(nama, popularity, image)
            startActivity(Intent(this, HomeActivity::class.java))
            Toast.makeText(this, "Movie baru berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        }

    }

}