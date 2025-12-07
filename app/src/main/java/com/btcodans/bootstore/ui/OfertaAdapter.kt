package com.btcodans.bootstore.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.btcodans.bootstore.data.models.Produto
import com.btcodans.bootstore.databinding.ItemOfertaEspecialBinding
import com.bumptech.glide.Glide

class OfertaAdapter(
    private val lista: List<Produto>,
    private val onClick: (Produto) -> Unit
) : RecyclerView.Adapter<OfertaAdapter.OfertaViewHolder>() {

    inner class OfertaViewHolder(val binding: ItemOfertaEspecialBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfertaViewHolder {
        val binding = ItemOfertaEspecialBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OfertaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfertaViewHolder, position: Int) {
        val produto = lista[position]
        holder.binding.apply {
            txtTitulo.text = produto.title
            txtPrecoDesconto.text = "R$  %.2f".format(produto.price)

            // preço original e com desconto
            val precoOriginal = produto.price + (produto.price * 0.40)
            txtPrecoOriginal.text = "R$ %.2f".format(precoOriginal)

            val desconto = ((precoOriginal - produto.price) / precoOriginal) * 100
            txtDesconto.text = "-${desconto.toInt()}%"

            Glide.with(imgProduto)
                .load(produto.thumbnail)
                .into(imgProduto)

            root.setOnClickListener { onClick(produto) }
        }
    }

    override fun getItemCount() = lista.size
}
