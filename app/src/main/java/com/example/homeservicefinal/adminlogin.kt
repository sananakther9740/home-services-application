package com.example.homeservicefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class adminlogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminlogin)

    }

    fun adminlogink(v: View?)
    {
        var u=findViewById<View>(R.id.aemailk) as EditText
        var p=findViewById<View>(R.id.apasswordk) as EditText
        if(u.text.toString()=="admin" && p.text.toString()=="admin123")
        {
            Toast.makeText(this,"Login success",Toast.LENGTH_LONG).show()
            var i= Intent(applicationContext,adminmenu::class.java)
            startActivity(i)

        }
        else
        {
            Toast.makeText(this,"Invalid Admin Credentials",Toast.LENGTH_LONG).show()
        }
    }

}