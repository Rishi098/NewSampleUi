package com.itamazons.whatsdp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class WhatsDpAdapter(
    private val itemList: ArrayList<String>
) :
    RecyclerView.Adapter<WhatsDpAdapter.ItemViewHolder>() {


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        Picasso.get()
            .load(itemList[position])
            .into(holder.pic)

    }

    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.whatsdp, parent, false)

        return ItemViewHolder(itemView)
    }


    override fun getItemCount() = itemList.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pic: ImageView = itemView.findViewById(R.id.image)

    }

}