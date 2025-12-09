package com.btcodans.bootstore.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.btcodans.bootstore.data.models.Postagem
import com.btcodans.bootstore.databinding.ItemPostagemBinding

class PostagensAdapter(
    private val lista: List<Postagem>,
    private val onClick: (Postagem) -> Unit
): RecyclerView.Adapter<PostagensAdapter.PostagensViewHolder> (){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostagensViewHolder = PostagensViewHolder(ItemPostagemBinding.inflate(
        LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(
        holder: PostagensViewHolder,
        position: Int
    ) {
        holder.vincularItensRecycler(lista[position])
    }

    override fun getItemCount(): Int = lista.size

    inner class PostagensViewHolder(val binding : ItemPostagemBinding):
        RecyclerView.ViewHolder (binding.root){

            //Vinculando itens no recycler (bind)

        fun vincularItensRecycler(postagem : Postagem){
            binding.txtTituloPostagem.text = postagem.title
            binding.txtBodyPostagem.text = postagem.body
            binding.txtAutor.text = "Autor: Usuário #${postagem.userId}"


            binding.root.setOnClickListener { onClick (postagem) }
        }
    }


}