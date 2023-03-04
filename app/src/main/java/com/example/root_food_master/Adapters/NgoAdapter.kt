package com.example.root_food_master.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.root_food_master.NgoScreen
import com.example.root_food_master.R
import com.example.root_food_master.model.ngo_info

class NgoAdapter (private val dataset : ArrayList<ngo_info>)
    : RecyclerView.Adapter<NgoAdapter.ItemViewHolder>() {

    lateinit var mListener1 : onItemClicklistener1
    lateinit var mListener2 : onItemClicklistener2
    interface onItemClicklistener1{
        fun onItemlick(position: Int)
    }
    interface onItemClicklistener2{
        fun onItemlick(position: Int)
    }
    fun setOnItemClickListener(listener1 : onItemClicklistener1 , listener2 : onItemClicklistener2){
        mListener1 = listener1
        mListener2 = listener2
    }

    class ItemViewHolder(private val view: View, listener1: onItemClicklistener1, listener2 : onItemClicklistener2) : RecyclerView.ViewHolder(view){
        val contactTV : TextView = view.findViewById(R.id.contact)
        val addressTV : TextView = view.findViewById(R.id.address)
        val nameTV : TextView = view.findViewById(R.id.name)
        val phBtn : Button = view.findViewById(R.id.call)
        val mapBtn : Button = view.findViewById(R.id.mapLocation)
        init {
            phBtn.setOnClickListener {
                listener1.onItemlick(adapterPosition)
            }
            mapBtn.setOnClickListener {
                listener2.onItemlick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.ngo_list,parent,false)//inflate actual item view
        return ItemViewHolder(adapterLayout,mListener1,mListener2)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item : ngo_info = dataset[position]
        holder.nameTV.text = item.name
        holder.contactTV.text = item.contact
        holder.addressTV.text = item.address
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}