package com.btcodans.bootstore.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.btcodans.bootstore.data.models.Carrinhos
import com.btcodans.bootstore.databinding.ActivityDetalhesCarrinhosBinding
import com.btcodans.bootstore.logo.BootStoreLogo
import com.btcodans.bootstore.ui.adapters.DetalhesCarrinhoAdapter

class DetalhesCarrinhosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityDetalhesCarrinhosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.txtLogo.text = BootStoreLogo()
        binding.btnHome.setOnClickListener { finish() }

        val carrinho: Carrinhos? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("Carrinhos", Carrinhos::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("Carrinhos")
        }

        if (carrinho != null) {
            configurarRecyclerView(carrinho)
            binding.txtWelcome.text = "ID Carrinho: ${carrinho.id}"
        } else {
            Toast.makeText(this, "Erro ao carregar dados do carrinho", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun configurarRecyclerView(carrinho: Carrinhos) {
        val adapterDetalhes = DetalhesCarrinhoAdapter(carrinho.products)

        binding.rvDetalhesCarrinho.apply {
            layoutManager = LinearLayoutManager(this@DetalhesCarrinhosActivity)
            adapter = adapterDetalhes
        }
    }
}