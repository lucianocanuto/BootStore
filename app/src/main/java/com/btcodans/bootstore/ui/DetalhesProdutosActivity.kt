package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.btcodans.bootstore.R
import com.btcodans.bootstore.databinding.ActivityDetalhesProdutosBinding
import com.btcodans.bootstore.logo.BootStoreLogo
import com.bumptech.glide.Glide

class DetalhesProdutosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesProdutosBinding.inflate(layoutInflater)
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
        binding.btnPostagens.setOnClickListener {
            startActivity(Intent(this, PostagensActivity::class.java))
        }

        binding.btnCarrinhos.setOnClickListener {
            startActivity(Intent(this, CarrinhosActivity::class.java))
        }


        // Receber dados da MainActivity
        val produtoId = intent.getIntExtra("ProdutoId", 0)
        val title = intent.getStringExtra("ProdutoTitle")       ?: ""
        val preco = intent.getDoubleExtra("ProdutoPreco",0.0)
        val thumb = intent.getStringExtra("ProdutoThumb")       ?: ""
        val descricao = intent.getStringExtra("ProdutoDescricao")     ?: ""
        val brand = intent.getStringExtra("ProdutoBrand")       ?: ""
        val categoria = intent.getStringExtra("ProdutoCat")     ?: ""
        val imagens = intent.getIntArrayExtra("ProdutoImagens") ?: ""
        val nota = intent.getDoubleExtra("ProdutoRating",0.0)


        Log.e("Teste","$preco")

        Glide.with(this)
            .load(thumb)
            .into(binding.posterImageView)

        val precoOK = "R$  %.2f".format(preco)

        binding.txtTitulo.text = title.toString()
        binding.txtPrecos.text = precoOK.toString()
        binding.txtDescricao.text = descricao.toString()
        binding.txtNota.text = "⭐ $nota"


    }
}