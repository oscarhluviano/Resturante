package com.example.resturante

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RestaurantViewHolder (view: View , onItemListener: RestaurantAdapter.OnItemListener) : RecyclerView.ViewHolder(view) , View.OnClickListener {

    private var ivItem: ImageView = view.findViewById(R.id.imageView)
    private var tvItemTitle: TextView = view.findViewById(R.id.tvNombreR)
    private var tvItemDesde: TextView = view.findViewById(R.id.tvAñoR)
    private var tvItemCalificacion: TextView = view.findViewById(R.id.tvCalificacionR)
    private var tvItemCostoPromedio: TextView = view.findViewById(R.id.tvCostoR)

    private val onItemListener = onItemListener
    private lateinit var restaurant: Restaurants

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View){
        onItemListener.clickRestaurant(restaurant)
    }

    fun bind(item: Restaurants)
    {
        tvItemTitle.text = item.nombre
        Picasso.get().load(item.fotos?.first()).into(ivItem)
        tvItemCalificacion.text = item.calificacion
        tvItemDesde.text = item.anioCreacion.toString()
        tvItemCostoPromedio.text = item.costoPromedio.toString()
        restaurant = item
    }

}
