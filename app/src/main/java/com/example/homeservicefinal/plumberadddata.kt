package com.example.homeservicefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_plumberadddata.*



class plumberadddata : AppCompatActivity() {
   // private lateinit var auth: FirebaseAuth   //firebase authenti
    private lateinit var db: FirebaseFirestore   //api for storage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plumberadddata)

        db = FirebaseFirestore.getInstance()

        plumberadd.setOnClickListener {

            var id = plumberid.text.toString()
            var Name = plumbername.text.toString()
            var phone = plumberphone.text.toString()
            var emails = plumberemails.text.toString()
            var addresss = plumberaddresss.text.toString()

            val docRef = db?.collection("Plumberdetails")?.document(id!!)

            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                val plumberdetails = documentSnapshot.toObject(plumberadddata::class.java) as plumbers?
                var u = plumbers(id, Name, phone,emails,addresss)
                db?.collection("plumbers")?.document(id!!)?.set(u)
                Toast.makeText(
                    this, "plumber added successfully",
                    Toast.LENGTH_LONG
                ).show()

            plumberid.setText("")
                plumbername.setText("")
                plumberphone.setText("")
                plumberemails.setText("")
                plumberaddresss.setText("")
            }

        }

        val actionBar= supportActionBar
        actionBar!!.title="PLUMBER DATA"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



class plumbers {
    var id=""
    var Name=""
    var phone=""
    var emails=""
    var addresss=""

    constructor(id:String,Name:String,phone:String,emails:String,addresss:String)
    {
        this.id=id
        this.Name=Name
        this.phone=phone
        this.emails=emails
        this.addresss=addresss

    }
    constructor()


}



