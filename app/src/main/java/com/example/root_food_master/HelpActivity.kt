package com.example.helpothers_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.root_food_master.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HelpActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)


        auth = FirebaseAuth.getInstance()
        val db = Firebase.firestore
        val PhoneNo: EditText = findViewById(R.id.PhoneNo)
        val button : Button=findViewById<Button>(R.id.send)
        button.setOnClickListener {
            val userid = FirebaseAuth.getInstance().currentUser!!.uid
            val phoneString =PhoneNo.text.toString().trim()
            val data_map = hashMapOf(
                "Doubt" to phoneString,
//                "E-mail" to email
            )
//            .document(userid)
            db.collection("Donor's_Info").add(data_map).addOnSuccessListener {
                Toast.makeText(this, "Your response Stored Succesfully", Toast.LENGTH_SHORT).show()
                PhoneNo.text.clear()
            }
        }
    }
}