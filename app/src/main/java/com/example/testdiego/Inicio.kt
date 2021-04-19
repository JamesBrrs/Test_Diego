package com.example.testdiego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_inicio.*
import org.json.JSONObject

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        //Acceso a colaboradores activity
        miscolab_btn.setOnClickListener(){
            val intent=Intent(this,Colaboradores::class.java)
            startActivity(intent)
        }

        //Acceso a registro de colaborador activity
        newcolab_btn.setOnClickListener(){
            val intent=Intent(this,AgregarColaborador::class.java)
            startActivity(intent)
        }

    }
}