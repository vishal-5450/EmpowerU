package com.example.root_food_master

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.root_food_master.Adapters.questionAdapter
import com.example.root_food_master.databinding.ActivityHomeScreenFinalBinding
import com.example.root_food_master.databinding.FragmentHomeBinding
import com.example.root_food_master.model.questions
import com.example.root_food_master.ui.home.HomeViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeScreenFinal : AppCompatActivity() {

    private var _binding: ActivityHomeScreenFinalBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<questions>
    private var db = Firebase.firestore

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = binding.rvQuestion
        recyclerView.layoutManager = LinearLayoutManager(this.applicationContext)
        arrayList = arrayListOf()
        db = FirebaseFirestore.getInstance()

        db.collection("ques").get()
            .addOnSuccessListener {
                if (!it.isEmpty) {
                    for (data in it.documents) {
                        val question: questions? = data.toObject(questions::class.java)
                        if (question != null) {
                            arrayList.add(question)
                        }
                    }
                    recyclerView.adapter = questionAdapter(arrayList)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this.applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}