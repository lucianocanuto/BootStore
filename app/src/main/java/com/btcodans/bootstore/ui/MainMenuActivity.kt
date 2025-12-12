package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.btcodans.bootstore.data.api.RetrofitConect
import com.btcodans.bootstore.data.models.Produto
import com.btcodans.bootstore.databinding.ActivityMainMenuBinding
import com.btcodans.bootstore.logo.BootStoreLogo
import com.btcodans.bootstore.ui.adapters.DestaqueAdapter
import com.btcodans.bootstore.ui.adapters.OfertaAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMenuActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainMenuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.txtLogo.text = BootStoreLogo()
        carregarDestaques()

        binding.btnProdutos.setOnClickListener {
            startActivity(Intent(this, ProdutosActivity::class.java))

        }
        binding.btnUsuarios.setOnClickListener {
            startActivity(Intent(this,UsuariosActivity::class.java))
        }
        binding.btnPostagens.setOnClickListener {
            startActivity(Intent(this, PostagensActivity::class.java))
        }

        binding.btnCarrinhos.setOnClickListener {
            startActivity(Intent(this, CarrinhosActivity::class.java))
        }


    }

    private fun carregarDestaques() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resposta = RetrofitConect.api.produtosDestaques()

                val lista = resposta.products

                withContext(Dispatchers.Main) {
                    recyclerDestaqueSet(lista)
                }

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    private fun recyclerDestaqueSet(list: List<Produto>) {

        // --- RECYCLER DE DESTAQUES ---
        binding.rvDestaques.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rvDestaques.adapter = DestaqueAdapter(list) { produto ->
            abrirDetalhes(produto)
        }

        // --- CARROSSEL DE OFERTAS ---
        val listaOfertas = gerarOfertas(list)

        binding.vpOfertas.adapter = OfertaAdapter(listaOfertas) { produto ->
            abrirDetalhes(produto)
        }

        binding.vpOfertas.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpOfertas.offscreenPageLimit = 3

        val offset = 60

        binding.vpOfertas.setPageTransformer { page, position ->
            page.translationX = -offset * position
            page.scaleY = 0.85f + (1 - kotlin.math.abs(position)) * 0.15f
            page.alpha = 0.7f + (1 - kotlin.math.abs(position)) * 0.3f
        }


        // --------------------------------------------------------
        // -------------------- AUTO PLAY --------------------------
        // --------------------------------------------------------

        val handler = android.os.Handler(mainLooper)

        val runnable = object : Runnable {
            override fun run() {

                val current = binding.vpOfertas.currentItem
                val total = listaOfertas.size

                val next = if (current == total - 1) 0 else current + 1

                binding.vpOfertas.setCurrentItem(next, true)

                handler.postDelayed(this, 3000) // 3 segundos
            }
        }

        handler.postDelayed(runnable, 3000)
    }





    private fun gerarOfertas(lista: List<Produto>): List<Produto> {
        // Exemplo: pega 8 produtos e aplica desconto random de 10 a 40%
        return lista.shuffled().take(8).map { p ->
            val desconto = (10..40).random()
            val precoComDesconto = p.price - (p.price * desconto / 100)

            p.copy(
                price = precoComDesconto,
            )
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