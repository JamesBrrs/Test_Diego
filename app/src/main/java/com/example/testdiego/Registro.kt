package com.example.testdiego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //Metodo de Registro
        registrar_btn.setOnClickListener(){
            if(reguser_edt.text.isNotEmpty() && regpas_edt.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(reguser_edt.text.toString(),regpas_edt.text.toString()).
                addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this,"Usuario registrado correctamente", Toast.LENGTH_LONG).show()
                        //Cambio a pagina de servicios
                        val intent=Intent(this,Servicios::class.java)
                        startActivity(intent)
                    }
                    else
                        Toast.makeText(this,"Fallo el Registro, rebice los datos y vuelva a intentarlo",
                            Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}