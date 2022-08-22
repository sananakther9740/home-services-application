package com.example.homeservicefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()    // firebase authentication data authentication
        db = FirebaseFirestore.getInstance()  // firebase firestore for data store

        adlogink.setOnClickListener {
            if (checking()) {
                val Email = aemailk.text.toString()
                val Password = apasswordk.text.toString()
                val Users = db.collection("USERS")
                val query = Users.whereEqualTo("Email", Email).get()
                    .addOnSuccessListener { tasks ->
                        if (tasks.isEmpty) {
                            auth.signInWithEmailAndPassword(Email, Password)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        var intent = Intent(this, menu::class.java)
                                        intent.putExtra("Email", Email)
                                        startActivity(intent)

                                    } else {
                                        Toast.makeText(this, "Wrong Details", Toast.LENGTH_LONG)
                                            .show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, "empty", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            }
            else {
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_LONG).show()

            }
        }
        val actionBar= supportActionBar
        actionBar!!.title="LOGIN"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
    }
    private fun checking(): Boolean {
        if (aemailk.text.toString().trim { it <= ' ' }.isNotEmpty()
            && apasswordk.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }
}




