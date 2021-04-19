package com.example.testdiego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_colaborador.*

class AgregarColaborador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_colaborador)

        //Metodo para agregar colaborador
        add_btn.setOnClickListener(){
            if(ncid_edt.text.isNotEmpty() && ncnombre_edt.text.isNotEmpty() && ncemail_edt.text.isNotEmpty()){
                Toast.makeText(this,"Colaborador agregado correctamente",Toast.LENGTH_LONG).show()
                val intent=Intent(this,Colaboradores::class.java)
                val b:Bundle= Bundle()
                //Pasamos los datos al activity de Colaboradores
                b.putString("ncid",ncid_edt.text.toString())
                b.putString("ncname",ncnombre_edt.text.toString())
                b.putString("ncorreo",ncemail_edt.text.toString())
                intent.putExtras(b)
                startActivity(intent)
            }
            else
                Toast.makeText(this,"Error, revisa los datos y vuelve a intentarlo",Toast.LENGTH_LONG).show()
        }
    }
}