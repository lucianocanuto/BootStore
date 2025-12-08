package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.btcodans.bootstore.R
import com.btcodans.bootstore.data.api.RetrofitConect
import com.btcodans.bootstore.data.models.Usuario
import com.btcodans.bootstore.databinding.ActivityUsuariosBinding
import com.btcodans.bootstore.logo.BootStoreLogo
import com.btcodans.bootstore.ui.adapters.UsuariosAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private fun configuraRecyclerView(list: List<Usuario>) {
        binding.RecyclerUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.RecyclerUser.adapter = UsuariosAdapter(list){ usuario ->
            enviarDetalhes(usuario)

        }
    }

    private fun buscarUsuarios() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val buscarUsuarios = RetrofitConect.api.listarUsuarios()

                Log.d("API", "Total: ${buscarUsuarios.users}, User: ${buscarUsuarios.users.size} ")
                Log.d("API", "Primeiro usuário: ${buscarUsuarios.users.firstOrNull()}")


                val listaUsuario = buscarUsuarios.users
                withContext(Dispatchers.Main){
                    configuraRecyclerView(listaUsuario)
                }

            }catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}

private fun enviarDetalhes(usuario: Usuario) {
    TODO("Not yet implemented")
}


