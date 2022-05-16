package com.example.resturante

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter (private val restaurants: ArrayList<Restaurants>, val onItemListener: OnItemListener) : RecyclerView.Adapter<RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(layoutInflater.inflate(R.layout.resturant_item,parent,false),onItemListener)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = restaurants[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    interface OnItemListener {
        fun clickRestaurant(restaurant: Restaurants)
    }

}