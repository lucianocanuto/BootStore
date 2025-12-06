package com.btcodans.bootstore.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.btcodans.bootstore.data.models.Produto
import com.btcodans.bootstore.databinding.ItemDestaqueBinding
import com.bumptech.glide.Glide

class DestaqueAdapter(
    private val produtos: List<Produto>,
    private val onClick: (Produto) -> Unit
) : RecyclerView.Adapter<DestaqueAdapter.DestaqueViewHolder>() {

    inner class DestaqueViewHolder(val binding: ItemDestaqueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(produto: Produto) {
            binding.txtTitulo.text = produto.title
            binding.txtPreco.text = "R$ ${produto.price}"
            Glide.with(binding.imgProduto.context)
                .load(produto.thumbnail)
                .into(binding.imgProduto)

            binding.root.setOnClickListener { onClick(produto) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DestaqueViewHolder(
            ItemDestaqueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DestaqueViewHolder, position: Int) =
        holder.bind(produtos[position])

    override fun getItemCount() = produtos.size
}
