package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.btcodans.bootstore.R
import com.btcodans.bootstore.databinding.ActivityDetalhesUsuariosBinding
import com.btcodans.bootstore.logo.BootStoreLogo
import com.bumptech.glide.Glide

class DetalhesUsuariosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesUsuariosBinding.inflate(layoutInflater)
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



        //Recebendo dados
        val id = intent.getIntExtra("UserId",0)
        val UserNome = intent.getStringExtra("UserNome") ?: ""
        val UserSobreNome = intent.getStringExtra("UserSobreNome") ?: ""
        val UserIdade = intent.getIntExtra("UserIdade",0)
        val Usersexo = intent.getStringExtra("Usersexo")  ?: ""
        val UserEmail = intent.getStringExtra("UserEmail")  ?: ""
        val UserOlhos = intent.getStringExtra("UserOlhos")  ?: ""
        val UserUniversidade = intent.getStringExtra("UserUniversidade")  ?: ""
        val UserPais = intent.getStringExtra("UserPais")  ?: ""
        val UserImagem = intent.getStringExtra("UserImagem")  ?: ""


        Glide.with(binding.posterImageView.context)
            .load(UserImagem)
            .into(binding.posterImageView)

        binding.textUserNome.text = UserNome
        binding.textUserSobreNome.text = UserSobreNome
        binding.textUserIdade.text = UserIdade.toString()
        binding.textUserSexo.text = Usersexo
        binding.textUserEmail.text = UserEmail
        binding.textUserOlhos.text = UserOlhos
        binding.textUserPais.text = UserPais
        binding.textUserUniversidade.text = UserUniversidade






    }
}