package com.example.testdiego

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_servicios.*
import org.json.JSONObject
import java.net.URI

class Servicios : AppCompatActivity() {

    var mydownloadid:Long=0
    var link=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        //Consumo de servicio
        consumo_btn.setOnClickListener(){
            val url="https://dl.dropboxusercontent.com/s/5u21281sca8gj94/getFile.json?dl=0/"
            //Llamada
            val queue= Volley.newRequestQueue(this)
            //Respuesta
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                Log.i("Respuesta Servicio", response)
                //Iteracion para ingresar al objeto data
                val JSONObject = JSONObject(response)
                val JSONObject2 = JSONObject.getJSONObject("data")
                link = JSONObject2.get("file").toString()
                Log.i("Respuesta Data", link)
                servicios_txt.text=response.toString()+"link de descarga: "+link
                descarga_btn.isEnabled=true
            }, {
                Log.e("Error consumo", "Algo salio mal")
            })
            queue.add(stringRequest)
        }
        Colabo_btn.setOnClickListener(){
            val intent=Intent(this,Inicio::class.java)
            startActivity(intent)
        }

        //Metodo de descarga a traves de URL
        descarga_btn.setOnClickListener(){
            val intent=Intent(Intent.ACTION_VIEW,Uri.parse(link))
            startActivity(intent)
        }
    }
}