package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.btcodans.bootstore.R
import com.btcodans.bootstore.data.api.RetrofitConect
import com.btcodans.bootstore.data.models.Postagem
import com.btcodans.bootstore.databinding.ActivityPostagensBinding
import com.btcodans.bootstore.logo.BootStoreLogo
import com.btcodans.bootstore.ui.adapters.PostagensAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostagensActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPostagensBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.txtLogo.text = BootStoreLogo()
        carregarPostagens()


    }

    private fun configuraRecylerView(list : List<Postagem>) {
        binding.rvPostagens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        
        binding.rvPostagens.adapter = PostagensAdapter (list){ postagem ->
            enviarIdPostagem(postagem)
            
            
        }
    }

    private fun carregarPostagens() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val buscarPostagens = RetrofitConect.api.listarPostagens()

                val listaPostagens = buscarPostagens.posts

                withContext(Dispatchers.Main){
                    configuraRecylerView(listaPostagens)
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}

private fun PostagensActivity.enviarIdPostagem(postagem: Postagem) {
    val intent = Intent(this, DetalhePostagemActivity::class.java)
    intent.putExtra("postagem", postagem)
    startActivity(intent)
}
