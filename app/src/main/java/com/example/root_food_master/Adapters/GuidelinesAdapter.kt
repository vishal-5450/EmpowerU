package com.example.root_food_master.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.root_food_master.R
import com.example.root_food_master.model.guidelinesClass

class GuidelinesAdapter(private var guideline: ArrayList<guidelinesClass>): RecyclerView.Adapter<GuidelinesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val info : TextView = itemView.findViewById(R.id.tv_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.guidelines_list,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return guideline.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = guideline[position]
        holder.name.text = guideline[position].name
        holder.info.text = guideline[position].info
    }
}