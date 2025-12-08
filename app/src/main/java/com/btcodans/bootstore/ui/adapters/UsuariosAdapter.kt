package com.btcodans.bootstore.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.btcodans.bootstore.data.models.Usuario
import com.btcodans.bootstore.databinding.ItemUsuariosBinding
import com.bumptech.glide.Glide

class UsuariosAdapter (
    private val lista: List<Usuario>,
    private val onClick: (Usuario) -> Unit
): RecyclerView.Adapter<UsuariosAdapter.UsuariosViewHolder>() {

    inner class UsuariosViewHolder(val binding : ItemUsuariosBinding):
        RecyclerView.ViewHolder(binding.root){

        //Vicular intens no recycler
        fun vivculadora (usuario: Usuario){
            Glide.with(binding.imgUser.context)
                .load(usuario.image)
                .into(binding.imgUser)
            binding.txtUserNome.text = usuario.firstName

            binding.root.setOnClickListener { onClick(usuario) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsuariosViewHolder (ItemUsuariosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(
        holder: UsuariosViewHolder,
        position: Int
    ) {
        holder.vivculadora(lista[position])
    }

    override fun getItemCount(): Int = lista.size


}
