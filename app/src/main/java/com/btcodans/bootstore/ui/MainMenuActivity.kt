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
        binding.rvDestaques.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapter = DestaqueAdapter(list) { produto ->
            // clique do produto (abre detalhes quando você criar)
        }

        binding.rvDestaques.adapter = adapter
    }
}
