package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.btcodans.bootstore.R
import com.btcodans.bootstore.data.api.RetrofitConect
import com.btcodans.bootstore.data.models.Produto
import com.btcodans.bootstore.databinding.ActivityProdutosBinding
import com.btcodans.bootstore.logo.BootStoreLogo
import com.btcodans.bootstore.ui.adapters.ProdutosAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdutosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProdutosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.txtLogo.text = BootStoreLogo()
        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
            finish()
        }
        binding.btnUsuarios.setOnClickListener {
            startActivity(Intent(this,UsuariosActivity::class.java))
            finish()
        }


        carregarProdutos()



        binding.rvProdutos

    }

    private fun configuraRecyclerView(listaProdutos: List<Produto>) {
        binding.rvProdutos.layoutManager =
            GridLayoutManager(this,2)
        binding.rvProdutos.adapter = ProdutosAdapter(listaProdutos){ produto ->
            abrirDetalhes(produto)

        }


    }

    private fun carregarProdutos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val buscarProdutos = RetrofitConect.api.listarProdutos()

                val listaProdutos = buscarProdutos.products

                withContext(Dispatchers.Main){
                    configuraRecyclerView(listaProdutos)
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
    private fun abrirDetalhes(produto: Produto) {
        //Eventos de click
        val intent = Intent(this, DetalhesProdutosActivity::class.java)
        intent.putExtra("ProdutoId", produto.id)
        intent.putExtra("ProdutoTitle", produto.title)
        intent.putExtra("ProdutoPreco", produto.price)
        intent.putExtra("ProdutoThumb", produto.thumbnail)
        intent.putExtra("ProdutoDescricao", produto.description)
        intent.putExtra("ProdutoBrand", produto.brand)
        intent.putExtra("ProdutoCat", produto.category)
        intent.putStringArrayListExtra("ProdutoImagens", ArrayList(produto.images ?:emptyList()))
        intent.putExtra("ProdutoRating", produto.rating)
        startActivity(intent)
    }

}

