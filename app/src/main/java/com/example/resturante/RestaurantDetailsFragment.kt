package com.example.resturante

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentResultListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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


class RestaurantDetailsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {
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
    private var lat: Double? = null
    private var lon: Double? = null

    private lateinit var map: GoogleMap

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
            lat = result.getString("latitud", "").toDouble()
            lon = result.getString("longitud", "").toDouble()


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
            createFragment()

        })
    }

    private fun createFragment(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    companion object {
        const val REQUEST_CODE_LOCATION=0
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled=true
            } else {
                Toast.makeText(context,"Para activar permisos, ve a ajustes y acepta los permisos", Toast.LENGTH_LONG).show()
            }

            else -> {}
        }
    }

    private fun crearMrker(lat: Double, lon: Double, txtUbicacion: String){
        val coordenadas = LatLng(lat,lon )
        val marker = MarkerOptions().position(coordenadas).title(txtUbicacion)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordenadas,18f),
            4000,null
        )

    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
        crearMrker(lat!!, lon!!,name!!)
    }

    override fun onMyLocationButtonClick(): Boolean {
        TODO("Not yet implemented")
    }

    override fun onMyLocationClick(p0: Location) {
        TODO("Not yet implemented")
    }
}