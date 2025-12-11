package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.btcodans.bootstore.R
import com.btcodans.bootstore.data.models.Postagem
import com.btcodans.bootstore.databinding.ActivityDetalhePostagemBinding
import com.btcodans.bootstore.logo.BootStoreLogo

class DetalhePostagemActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhePostagemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.txtLogo.text = BootStoreLogo()

        binding.btnProdutos.setOnClickListener {
            startActivity(Intent(this, ProdutosActivity::class.java))

        }
        binding.btnUsuarios.setOnClickListener {
            startActivity(Intent(this,UsuariosActivity::class.java))
        }


        binding.btnCarrinhos.setOnClickListener {
            startActivity(Intent(this, CarrinhosActivity::class.java))
        }
        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))

        }

        val postagem = intent.getParcelableExtra<Postagem>("postagem")

        postagem?.let {
            binding.txtTituloDetalhe.text = it.title
            binding.txtBodyCompleto.text = it.body
            binding.txtAutorDetalhe.text = "Autor: Usuário #${it.userId}"
        }
    }
}