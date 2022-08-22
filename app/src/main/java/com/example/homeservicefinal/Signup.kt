package com.example.homeservicefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*


class Signup :AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        Continue.setOnClickListener {
            if (checking()) {
                var Email = useremail.text.toString()
                var Password = userpassword.text.toString()
                var name = username.text.toString()
                var phone = userphone.text.toString()


                val user = hashMapOf(
                    "Name" to name,
                    "Phone" to phone,
                    "email" to Email
                )
                val Users = db.collection("USERS")
                val query = Users.whereEqualTo("email", Email).get()
                    .addOnSuccessListener { tasks ->
                        if (tasks.isEmpty) {
                            auth.createUserWithEmailAndPassword(Email, Password)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        Users.document(Email).set(user)
                                        val intent = Intent(this, Login::class.java)
                                        intent.putExtra("email", Email)
                                        startActivity(intent)

                                    } else {
                                        Toast.makeText(
                                            this,
                                            " Failed",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, "User Already Registered", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                        }
                    }
            } else {
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_LONG).show()
            }
        }
        val actionBar= supportActionBar
        actionBar!!.title="SIGNUP"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun checking(): Boolean {
        if (username.text.toString().trim { it <= ' ' }.isNotEmpty()
            && userphone.text.toString().trim { it <= ' ' }.isNotEmpty()
            && useremail.text.toString().trim { it <= ' ' }.isNotEmpty()
            && userpassword.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }
     fun notregister(v: View?){

        val intent = Intent(applicationContext, Login::class.java)
        startActivity(intent)
        finish()


    }
}
