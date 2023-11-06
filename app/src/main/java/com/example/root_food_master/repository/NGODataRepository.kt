package com.example.root_food_master.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.root_food_master.NGODataModel
import com.example.root_food_master.model.NGODataModelFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NGODataRepository {

    @Volatile private var INSTANCE : NGODataRepository? = null

    fun getInstance() : NGODataRepository {
        return INSTANCE ?: synchronized(this){
            val instance = NGODataRepository()
            INSTANCE = instance
            instance
        }
    }

    fun getNGOData(vegetablesList : MutableLiveData<List<NGODataModelFirebase>>) {
        val database = FirebaseDatabase.getInstance()
        database.getReference("NGO").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val vegetablesListItem : List<NGODataModelFirebase> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(NGODataModelFirebase::class.java)!!
                    }

                    vegetablesList.postValue(vegetablesListItem)
                } catch (e : Exception) {
                    Log.d("repoexception", e.message.toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FirebaseError", error.message)
            }
        })
    }
}