package com.example.resturante

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantsFragment : Fragment(), RestaurantAdapter.OnItemListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter : RestaurantAdapter
    private val restaurantsImages = ArrayList<Restaurants>()

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
        return inflater.inflate(R.layout.fragment_restaurants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllRestaurants()
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View){
        adapter = RestaurantAdapter(restaurantsImages, this)
        view.findViewById<RecyclerView>(R.id.rvRestaurants).layoutManager = LinearLayoutManager(this.context)
        view.findViewById<RecyclerView>(R.id.rvRestaurants).addItemDecoration(DividerItemDecoration(context,1))
        view.findViewById<RecyclerView>(R.id.rvRestaurants).adapter = adapter

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo2495997.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllRestaurants(){
        Toast.makeText(this.context,"¡Nuestras mejores recomendaciones!", Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<RestaurantResponse> = getRetrofit().create(APIService::class.java).getAllRestaurants("restaurantes2")
            val allRestaurants:RestaurantResponse? = call.body()

            activity?.runOnUiThread {
                if(call.isSuccessful){
                    var restaurants: ArrayList<Restaurants> = (allRestaurants?.restaurantes ?: emptyArray<RestaurantResponse>()) as ArrayList<Restaurants>
                    restaurantsImages.clear()
                    restaurantsImages.addAll(restaurants)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun clickRestaurant(item: Restaurants) {
        Toast.makeText(this.context, "Movie: ${item.nombre}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this.context,RestaurantDetailsFragment::class.java)
        intent.putExtra("nombre" , item.nombre)
        intent.putExtra("calificacion" , item.calificacion)
        intent.putExtra("anio" , item.anioCreacion)
        intent.putExtra("costo" , item.costoPromedio)
        intent.putExtra("direccion" , item.direccion)
        intent.putExtra("resenia" , item.resenia)
        intent.putExtra("latitud" , item.latitud)
        intent.putExtra("longitud" , item.longitud)
        intent.putExtra("foto1" , item.fotos?.get(0))
        intent.putExtra("foto2" , item.fotos?.get(1))
        intent.putExtra("foto3" , item.fotos?.get(2))
        findNavController().navigate(R.id.action_restaurantsFragment_to_restaurantDetailsFragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}