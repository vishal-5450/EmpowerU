package com.example.root_food_master.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.root_food_master.NGODataModel
import com.example.root_food_master.model.NGODataModelFirebase
import com.example.root_food_master.repository.NGODataRepository

class NGODataViewModel: ViewModel() {

    private  val repository : NGODataRepository
    private val allVegetables = MutableLiveData<List<NGODataModelFirebase>>()

    val allVegetablesList : LiveData<List<NGODataModelFirebase>> = allVegetables


    init {
        repository = NGODataRepository().getInstance()
        repository.getNGOData(allVegetables)
    }

    fun updateAllVegetablesList(vegetablesList: List<NGODataModelFirebase>): List<NGODataModelFirebase> {
        allVegetables.value = vegetablesList
        return vegetablesList
    }


}