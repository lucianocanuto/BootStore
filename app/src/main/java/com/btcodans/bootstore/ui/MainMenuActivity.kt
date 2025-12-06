package com.btcodans.bootstore.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.btcodans.bootstore.data.api.RetrofitConect
import com.btcodans.bootstore.data.models.Produto
import com.btcodans.bootstore.databinding.ActivityMainMenuBinding
import com.btcodans.bootstore.logo.BootStoreLogo
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


        // --- RECYCLER DE OFERTAS ESPECIAIS ---
        val listaOfertas = gerarOfertas(list)

        binding.rvOfertas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rvOfertas.adapter = OfertaAdapter(listaOfertas) { produto ->
            abrirDetalhes(produto)
        }
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
        // implementar depois
    }

}
