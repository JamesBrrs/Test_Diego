package com.example.testdiego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testdiego.models.Empleado
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_colaboradores.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import kotlin.collections.ArrayList

class Colaboradores : AppCompatActivity(), OnMapReadyCallback {
    var banderapers=true
    private val key="MY_KEY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colaboradores)
        createFragment()

        //Recpecion de datos del registro de nuevos colaboradores
        var bundle=intent.extras
        // Instance de colaboradores
        val usersList: ArrayList<Empleado> = ArrayList()

        try {
            //Json Object
            val obj = JSONObject(getJSONFromAssets()!!)
            //Iteracion Json Object para entrar en data
            val obj2= obj.getJSONObject("data")
            //Arreglo Json de empleados
            val usersArray = obj2.getJSONArray("employees")

            //Recorremos el arreglo
            for (i in 0 until usersArray.length()) {
                //Empleado simple
                val user = usersArray.getJSONObject(i)
                //Obtenemos sus valores de la clase
                val id = user.getString("id")
                val name = user.getString("name")
                val email = user.getString("mail")

                //Objeto location
                val loc = user.getJSONObject("location")

                val loclat = loc.getString("lat")
                val loclog = loc.getString("log")

                //Objetos con los valores individuales
                val userDetails =
                    Empleado(id, name, email, loclat,loclog)

                //Llenamos la lista
                usersList.add(userDetails)
            }

            //Metodo de nuevos coloboradores agregados
            val nuevaid= bundle?.getString("ncid")
            val nuevaname= bundle?.getString("ncname")
            val nuevaemail= bundle?.getString("ncorreo")
            var bandera=true
            if(nuevaid!=null && nuevaname!=null && nuevaemail!=null){
                for (i in 0 until usersArray.length()){
                    val user = usersArray.getJSONObject(i)
                    val id = user.getString("id")
                    if(id==nuevaid){
                        bandera=false
                    }
                }
                if(bandera==true){
                    val userDetails =
                        Empleado(nuevaid, nuevaname, nuevaemail, "19.3022345", "-99.2024537")
                    usersList.add(userDetails)
                }
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }


        rvColab.layoutManager = LinearLayoutManager(this)
        val itemAdapter = EmpleadoAdapter(this, usersList)
        //Inflamos el recyclerview
        rvColab.adapter = itemAdapter

    }


    //Lectura de archivo JSON
    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("employees_data.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    //Configuracion de Mapa
    private lateinit var map:GoogleMap
    private fun createFragment(){
        val mapFragment=supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    //Creacion de mapa
    override fun onMapReady(googleMap: GoogleMap) {
        map=googleMap
        createMarker()
    }

    //Marcadores a traves del mismo metodo con el que se llena la lista de empleados con la incorporacion del
    //marcador en cada uno
    private fun createMarker(){
        var bundle=intent.extras
        val usersList: ArrayList<Empleado> = ArrayList()

        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val obj2= obj.getJSONObject("data")
            val usersArray = obj2.getJSONArray("employees")

            for (i in 0 until usersArray.length()) {
                val user = usersArray.getJSONObject(i)
                val id = user.getString("id")
                val name = user.getString("name")
                val email = user.getString("mail")

                val loc = user.getJSONObject("location")
                val loclat = loc.getString("lat")
                val loclog = loc.getString("log")

                val cordinates=LatLng(loclat.toDouble(), loclog.toDouble())
                val marker=MarkerOptions().position(cordinates).title(name)
                map.addMarker(marker)
                val userDetails =
                    Empleado(id, name, email, loclat,loclog)

                usersList.add(userDetails)
            }

            val nuevaid= bundle?.getString("ncid")
            val nuevaname= bundle?.getString("ncname")
            val nuevaemail= bundle?.getString("ncorreo")
            var bandera=true
            if(nuevaid!=null && nuevaname!=null && nuevaemail!=null){
                for (i in 0 until usersArray.length()){
                    val user = usersArray.getJSONObject(i)
                    val id = user.getString("id")
                    if(id==nuevaid){
                        bandera=false
                    }
                }
                if(bandera==true){
                    val userDetails =
                        Empleado(nuevaid, nuevaname, nuevaemail, "19.3022345", "-99.2024537")
                    usersList.add(userDetails)
                    val cordinates=LatLng(19.3022345, -99.2024537)
                    val marker=MarkerOptions().position(cordinates).title(nuevaname)
                    map.addMarker(marker)
                }
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
    }
}