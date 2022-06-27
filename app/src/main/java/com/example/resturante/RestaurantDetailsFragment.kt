package com.example.resturante

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class RestaurantDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var  name: String? = null
    private var photo1: String? = null
    private var photo2: String? = null
    private var photo3: String? = null
    private var review: String? = null
    private var address: String? = null
    private var date: String? = null
    private var score: String? = null
    private var cost: String? = null
    private var lat: String? = null
    private var lon: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("datosR", this, FragmentResultListener { requestKey, result ->
            name = result.getString("nombre", "")
            photo1 = result.getString("foto1", "")
            photo2 = result.getString("foto2", "")
            photo3= result.getString("foto3", "")
            review = result.getString("resenia", "")
            address = result.getString("direccion", "")
            date = result.getString("anio", "")
            score = result.getString("calificacion", "")
            cost = result.getString("costo", "")


            var ivItem = view.findViewById<ImageView>(R.id.ivMain)
            Picasso.get().load(photo1).into(ivItem)
            var ivItem1 = view.findViewById<ImageView>(R.id.ivPhoto1)
            Picasso.get().load(photo1).into(ivItem1)
            var ivItem2 = view.findViewById<ImageView>(R.id.ivPhoto2)
            Picasso.get().load(photo2).into(ivItem2)
            var ivItem3 = view.findViewById<ImageView>(R.id.ivPhoto3)
            Picasso.get().load(photo3).into(ivItem3)
            view.findViewById<TextView>(R.id.tvNombreD).text = name
            view.findViewById<TextView>(R.id.tvFechaD).text = date
            view.findViewById<TextView>(R.id.tvCalificacionD).text = score
            view.findViewById<TextView>(R.id.tvCostoD).text = cost
            view.findViewById<TextView>(R.id.tvResenaD).text = review
            view.findViewById<TextView>(R.id.tvUbicacionD).text = address
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}