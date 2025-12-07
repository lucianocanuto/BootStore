package com.btcodans.bootstore.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.btcodans.bootstore.data.models.Produto
import com.btcodans.bootstore.databinding.ItemProdutosBinding
import com.bumptech.glide.Glide

class ProdutosAdapter(
    private val lista: List<Produto>,
    private val onClick: (Produto) -> Unit
) : RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder>(){

    inner class ProdutosViewHolder(val binding : ItemProdutosBinding )
        : RecyclerView.ViewHolder(binding.root){

            //bind-> vincular
            fun bind( produto: Produto){
                Glide.with(binding.poster.context)
                    .load(produto.thumbnail)
                    .into(binding.poster)

                binding.title.text = produto.title
                binding.avaliacao.text = "Avaliações:   ⭐ ${produto.rating}"
                binding.preco.text = "R$ ${produto.price}"

                binding.root.setOnClickListener { onClick(produto) }

            }
        }



    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int)=
        ProdutosViewHolder(
            ItemProdutosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(
        holder: ProdutosViewHolder,
        position: Int
    ) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size


}