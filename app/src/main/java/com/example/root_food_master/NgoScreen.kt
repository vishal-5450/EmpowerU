package com.example.root_food_master

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.root_food_master.Adapters.NgoAdapter
import com.example.root_food_master.databinding.ActivityNgoScreenBinding
import com.example.root_food_master.model.ngo_info
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NgoScreen : AppCompatActivity() {
    lateinit var binding: ActivityNgoScreenBinding
    var db = Firebase.firestore
    lateinit var datalist: ArrayList<ngo_info>
    lateinit var adapterRc : NgoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityNgoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        findViewById<Button>(R.id.call).setOnClickListener {
//            val dialIntent = Intent(Intent.ACTION_DIAL)
//            val phone : String = findViewById<EditText>(R.id.number).text.toString()
//            dialIntent.data = Uri.parse("tel:$phone")
//            startActivity(dialIntent)
//        }
        val recyclerView = binding.rv
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        datalist = arrayListOf()
        adapterRc = NgoAdapter(datalist)
        recyclerView.adapter = adapterRc
        EventChangeListenerAll()
        adapterRc.setOnItemClickListener(object : NgoAdapter.onItemClicklistener1{
            override fun onItemlick(position: Int) {
                val data = datalist[position]
                val dialIntent = Intent(Intent.ACTION_DIAL)
                val phone : String = data.contact.toString()
                dialIntent.data = Uri.parse("tel:$phone")
                startActivity(dialIntent)
            }
        },object :NgoAdapter.onItemClicklistener2{
            override fun onItemlick(position: Int) {
                val data = datalist[position]
                startActivity(Intent(this@NgoScreen,GoogleMaps::class.java))
            }
        })
    }
    private fun EventChangeListenerAll()  {
        db = FirebaseFirestore.getInstance()
        db.collection("ngo_info")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value : QuerySnapshot?,
                    error : FirebaseFirestoreException?
                ){
                    if (error != null){
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }
                    for(dc : DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            datalist.add(dc.document.toObject(ngo_info::class.java))
                        }
                    }
                    adapterRc.notifyDataSetChanged()
                }
            })

    }
}