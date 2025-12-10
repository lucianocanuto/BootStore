package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.btcodans.bootstore.data.api.RetrofitConect
import com.btcodans.bootstore.data.models.Carrinhos
import com.btcodans.bootstore.databinding.ActivityCarrinhosBinding
import com.btcodans.bootstore.logo.BootStoreLogo
import com.btcodans.bootstore.ui.adapters.CarrinhosAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarrinhosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCarrinhosBinding.inflate(layoutInflater)
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


        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))

        }

        carregarCarrinhos()

        binding.rvCarrinhos
    }

    private fun carregarCarrinhos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitConect.api.listarCarrinhos()

                if(response.isSuccessful){
                    val dados = response.body()

                    val listaDeCarrinhos = dados?.carts?: emptyList()

                    Log.i("API_TEST", "Carrinhos encontrados: ${listaDeCarrinhos.size}")

                    withContext(Dispatchers.Main){
                        configuraRecyclerView(listaDeCarrinhos)
                    }
                }else{
                    Log.i("API_TEST", "Erro: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("API_TEST", "Falha na conexão: ${e.message}")
            }
        }
    }

    private fun configuraRecyclerView(listaCarrinhos: List<Carrinhos>) {
        binding.rvCarrinhos.layoutManager =
            GridLayoutManager(this,2)
        binding.rvCarrinhos.adapter = CarrinhosAdapter(listaCarrinhos){ carrinho ->
            abrirDetalhes(carrinho)
        }
    }

    private fun abrirDetalhes(carrinhos: Carrinhos) {
        //Eventos de click
        val intent = Intent(this, DetalhesCarrinhosActivity::class.java)
        intent.putExtra("Carrinhos", carrinhos)
        //intent.putExtra("CarrinhoTitle", carrinho.title)
        //intent.putExtra("CarrinhoPreco", carrinho.price)
        //intent.putExtra("CarrinhoThumb", carrinho.thumbnail)
        //intent.putStringArrayListExtra("CarrinhoImagens", ArrayList(carrinho.images ?:emptyList()))
        startActivity(intent)
    }
}