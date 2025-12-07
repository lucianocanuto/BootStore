package com.btcodans.bootstore.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.btcodans.bootstore.R
import com.btcodans.bootstore.databinding.ActivityDetalhesProdutosBinding

class DetalhesProdutosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesProdutosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Receber dados da MainActivity
        val produtoId = intent.getIntExtra("ProdutoId", 0)
        val title = intent.getStringExtra("ProdutoTitle")       ?: ""
        val preco = intent.getStringExtra("ProdutoPreco")       ?: ""
        val thumb = intent.getStringExtra("ProdutoThumb")       ?: ""
        val desconto = intent.getStringExtra("ProdutoDesc")     ?: ""
        val brand = intent.getStringExtra("ProdutoBrand")       ?: ""
        val categoria = intent.getStringExtra("ProdutoCat")     ?: ""
        val imagens = intent.getIntArrayExtra("ProdutoImagens") ?: ""
        val nota = intent.getStringExtra("ProdutoRating")       ?: ""


    }
}