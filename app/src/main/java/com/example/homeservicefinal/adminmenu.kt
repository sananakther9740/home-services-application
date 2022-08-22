package com.example.homeservicefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class adminmenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminmenu)
    }
    fun plumber(v: View?){
        intent = Intent(this,plumberadddata::class.java)
        startActivity(intent)

    }
    fun electrician(v: View?){
        intent = Intent(this,electricianadddata::class.java)
        startActivity(intent)

    }
    fun technician(v: View?){
        intent = Intent(this,technicianadddata::class.java)
        startActivity(intent)

    }
}