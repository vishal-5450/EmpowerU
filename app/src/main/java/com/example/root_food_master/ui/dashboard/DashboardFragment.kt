package com.example.root_food_master.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helpothers_module.HelpActivity
import com.example.root_food_master.FoodSecurity
import com.example.root_food_master.NGODataActivity
import com.example.root_food_master.NgoScreen
import com.example.root_food_master.databinding.FragmentDashboardBinding
import com.example.root_food_master.model.questions
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //food
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.cardView1.setOnClickListener {
            startActivity(Intent(this.context, FoodSecurity::class.java))
        }
        binding.cardView2.setOnClickListener{
            startActivity(Intent(this.context, NGODataActivity::class.java))
        }
        binding.cardView3.setOnClickListener {
            startActivity(Intent(this.context, NgoScreen::class.java))
        }
        binding.cardView4.setOnClickListener {
            startActivity(Intent(this.context,HelpActivity::class.java))
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding != null
    }
}