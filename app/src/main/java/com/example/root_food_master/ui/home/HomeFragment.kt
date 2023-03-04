package com.example.root_food_master.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.root_food_master.Adapters.questionAdapter
import com.example.root_food_master.FoodSecurity
import com.example.root_food_master.NgoScreen
import com.example.root_food_master.databinding.FragmentDashboardBinding
import com.example.root_food_master.databinding.FragmentHomeBinding
import com.example.root_food_master.model.questions
import com.example.root_food_master.ui.dashboard.DashboardViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<questions>
    private var db = Firebase.firestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // health
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.rvQuestion
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        arrayList = arrayListOf()
        db = FirebaseFirestore.getInstance()

        db.collection("ques").get()
            .addOnSuccessListener {
                if(!it.isEmpty ){
                    for(data in it.documents){
                        val question : questions? = data.toObject(questions::class.java)
                        if (question != null) {
                            arrayList.add(question)
                        }
                    }
                    recyclerView.adapter = questionAdapter(arrayList)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this.context,it.toString(), Toast.LENGTH_SHORT).show()
            }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}