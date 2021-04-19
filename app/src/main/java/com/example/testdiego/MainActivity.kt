package com.example.testdiego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Metodo de Ingreso
        ingresar_btn.setOnClickListener(){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(usemail_edt.text.toString(),usepas_edt.text.toString()).
            addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
                    val intent=Intent(this,Servicios::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Acceso incorrecto, revice sus datos y vuelva a intentarlo"
                        , Toast.LENGTH_LONG).show()
                    usepas_edt.text.clear()
                }
            }
        }

        //Acceso a Registro
        registro_btn.setOnClickListener(){
            val intent=Intent(this,Registro::class.java)
            startActivity(intent)
        }
    }
}