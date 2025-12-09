package com.btcodans.bootstore.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.btcodans.bootstore.data.models.CarrinhosDetalhes
import com.btcodans.bootstore.databinding.ItemCarrinhoDetalhesBinding
import com.bumptech.glide.Glide

class DetalhesCarrinhoAdapter(
    private val lista: List<CarrinhosDetalhes>
): RecyclerView.Adapter<DetalhesCarrinhoAdapter.DetalhesCarrinhoViewHolder>() {

    inner class DetalhesCarrinhoViewHolder(val binding: ItemCarrinhoDetalhesBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(carrinhoDetalhes: CarrinhosDetalhes){
                Glide.with(binding.poster.context)
                    .load(carrinhoDetalhes.thumbnail)
                    .into(binding.poster)

                binding.tituloProduto.text = carrinhoDetalhes.title
                binding.quantidade.text = "Quantidade: x${carrinhoDetalhes.quantity}"
                binding.preco.text = String.format("Preço Unitário: R$ %.2f", carrinhoDetalhes.price)
            }

    }

    // Cria o ViewHolder (infla o layout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalhesCarrinhoViewHolder {
        val binding = ItemCarrinhoDetalhesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetalhesCarrinhoViewHolder(binding)
    }

    // Preenche os dados
    override fun onBindViewHolder(holder: DetalhesCarrinhoViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size
}