package com.example.root_food_master.Adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.root_food_master.NGODataModel
import com.example.root_food_master.R
import com.example.root_food_master.model.NGODataModelFirebase

var cartItemsList = ArrayList<NGODataModelFirebase>()

class NGODataAdapter() :
    RecyclerView.Adapter<NGODataAdapter.VegetablesViewHolder>(){

    private var vegetablesList = ArrayList<NGODataModelFirebase>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VegetablesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ngo_data_list_item, parent, false)
        return VegetablesViewHolder(view)
    }

    override fun onBindViewHolder(holder: VegetablesViewHolder, position: Int) {
        val vegetableItem = vegetablesList[position]
        holder.bind(vegetableItem,)
    }

    override fun getItemCount(): Int {
        return vegetablesList.size
    }

    fun updateData(newVegetablesList: List<NGODataModelFirebase>) {
        this.vegetablesList.clear()
        this.vegetablesList.addAll(newVegetablesList)
        notifyDataSetChanged()
    }

    class VegetablesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var description : TextView = itemView.findViewById(R.id.description)
        var organization : TextView = itemView.findViewById(R.id.reciever_org)
        var title : TextView = itemView.findViewById(R.id.titles)
        var transactionValue : TextView = itemView.findViewById(R.id.transaction_value)


        fun bind(vegetableItem : NGODataModelFirebase) {

            description.text = vegetableItem.titles
            organization.text = vegetableItem.receiver_org
            title.text = vegetableItem.description
            transactionValue.text = vegetableItem.transaction_value.toString()

        }
    }

}