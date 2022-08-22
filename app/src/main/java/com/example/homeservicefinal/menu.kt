package com.example.homeservicefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
    fun plumb(v: View?){
        intent = Intent(this,plumber_page::class.java)
        startActivity(intent)

    }
    fun electr(v: View?){
        intent = Intent(this,electrician_page::class.java)
        startActivity(intent)

    }
    fun techni(v: View?){
        intent = Intent(this,technician_page::class.java)
        startActivity(intent)

    }

}