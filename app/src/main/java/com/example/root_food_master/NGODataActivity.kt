package com.example.root_food_master

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.root_food_master.Adapters.NGODataAdapter
import com.example.root_food_master.databinding.ActivityNgodataBinding
import com.example.root_food_master.viewmodel.NGODataViewModel

class NGODataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNgodataBinding
    private lateinit var viewModel: NGODataViewModel
    private lateinit var adapter: NGODataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNgodataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[NGODataViewModel::class.java]

        binding.ngoDataRv.layoutManager = LinearLayoutManager(this)
        binding.ngoDataRv.setHasFixedSize(true)
        adapter = NGODataAdapter()

        binding.ngoDataRv.adapter = adapter
        viewModel.allVegetablesList.observe(this) {
            adapter.updateData(it)
        }


    }
}