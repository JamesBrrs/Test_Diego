package com.example.testdiego

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testdiego.models.Empleado
import kotlinx.android.synthetic.main.colaboradores_vista.view.*

class EmpleadoAdapter(val context: Context, val items: ArrayList<Empleado>) :
    RecyclerView.Adapter<EmpleadoAdapter.ViewHolder>() {


    //Inflamos la vista de los items ViewHolhder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.colaboradores_vista,
                parent,
                false
            )
        )
    }

    //Unir Arraylist a la vista para inlfar la vista xml
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tvId.text = item.id
        holder.tvName.text = item.name
        holder.tvEmail.text = item.mail
    }

    //Tamaño
    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val tvId = view.tv_id
        val tvName = view.tv_name
        val tvEmail = view.tv_email
    }
}