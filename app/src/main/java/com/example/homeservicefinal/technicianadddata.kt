package com.example.homeservicefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_electricianadddata.*

import kotlinx.android.synthetic.main.activity_plumberadddata.*
import kotlinx.android.synthetic.main.activity_technicianadddata.*


class technicianadddata : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technicianadddata)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        technicianadd.setOnClickListener {

            var id = technicianid.text.toString()
            var Name = technicianname.text.toString()
            var phone = technicianphone.text.toString()
            var address = technicianad.text.toString()
            var email = technicianem.text.toString()
            val docRef = db?.collection("Techniciandetails")?.document(id!!)

            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                val techniciandetail = documentSnapshot.toObject(technicianadddata::class.java) as technicians?
                var u = technicians(id, Name, phone,email,address)
                db?.collection("Technician")?.document(id!!)?.set(u)
                Toast.makeText(
                    this, "technician added successfully",
                    Toast.LENGTH_LONG
                ).show()
                technicianid.setText("")
                technicianname.setText("")
                technicianphone.setText("")
                technicianad.setText("")
                technicianem.setText("")

            }
        }
        val actionBar= supportActionBar
        actionBar!!.title="TECHNICIAN DATA"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)



    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}


class technicians {
    var id=""
    var Name=""
    var phone=""
    var email=""
    var address=""

    constructor(id:String,Name:String,phone:String,email:String,address:String)
    {
        this.id=id
        this.Name=Name
        this.phone=phone
        this.address=address
        this.email=email

    }
    constructor()


}



