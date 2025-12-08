package com.btcodans.bootstore.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.btcodans.bootstore.data.models.Carrinhos
import com.btcodans.bootstore.databinding.ItemCarrinhosBinding
import com.bumptech.glide.Glide

class CarrinhosAdapter(
    private val lista: List<Carrinhos>,
    private val onClick: (Carrinhos) -> Unit
) : RecyclerView.Adapter<CarrinhosAdapter.CarrinhosViewHolder>() {

    inner class CarrinhosViewHolder(val binding: ItemCarrinhosBinding)
        : RecyclerView.ViewHolder(binding.root){

        //bind-> vincular
        fun bind( carrinho: Carrinhos){
            Glide.with(binding.poster.context)
                .load(carrinho.products[0].thumbnail)
                .into(binding.poster)

            binding.idUsuario.text = "ID Usuário: ${carrinho.userId}"
            binding.totalProdutos.text = "Total de Produtos: ${carrinho.totalProducts}"
            binding.preco.text = "Total do Carrinho: R$${carrinho.total}"

            binding.root.setOnClickListener { onClick(carrinho) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CarrinhosViewHolder(
            ItemCarrinhosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CarrinhosViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size
}