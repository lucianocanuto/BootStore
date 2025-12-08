package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.btcodans.bootstore.R
import com.btcodans.bootstore.databinding.ActivityUsuariosBinding
import com.btcodans.bootstore.logo.BootStoreLogo

class UsuariosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUsuariosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.txtLogo.text = BootStoreLogo()

        buscarUsuarios()


        binding.btnProdutos.setOnClickListener {
            startActivity(Intent(this, ProdutosActivity::class.java))
            finish()
        }
        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
            finish()
        }


    }

    private fun buscarUsuarios() {
        TODO("Not yet implemented")
    }
}