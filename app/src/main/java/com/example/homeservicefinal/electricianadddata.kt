package com.example.homeservicefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_electricianadddata.*


class electricianadddata : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electricianadddata)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        electricianadd.setOnClickListener {

            var id = electricianid.text.toString()
            var Name = electricianname.text.toString()
            var phone = electricianphone.text.toString()
            var email = electricianema.text.toString()
            var address =electricianadddd.text.toString()
            val docRef = db?.collection("ElectricianDetails")?.document(id!!)
    
            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                val electriciandetails = documentSnapshot.toObject(plumberadddata::class.java) as electricians?
                var u = electricians(id, Name, phone,address,email)
                db?.collection("Electrician")?.document(id!!)?.set(u)
                Toast.makeText(
                    this, "electrician added successfully",
                    Toast.LENGTH_LONG
                ).show()
                electricianid.setText("")
                electricianname.setText("")
                electricianphone.setText("")
                electricianema.setText("")
                electricianadddd.setText("")


            }
        }
        val actionBar= supportActionBar
        actionBar!!.title="ELECTRICIAN DATA"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}


class electricians {
    var id=""
    var Name=""
    var phone=""
    var address=""
    var email=""

    constructor(id:String,Name:String,phone:String,address:String,email:String)
    {
        this.id=id
        this.Name=Name
        this.phone=phone
        this.address=address
        this.email=email

    }
    constructor()


}



