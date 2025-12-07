package com.btcodans.bootstore.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.btcodans.bootstore.databinding.ActivityCarrinhosBinding
import com.btcodans.bootstore.logo.BootStoreLogo

class CarrinhosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCarrinhosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.txtLogo.text = BootStoreLogo()
        binding.btnHome.setOnClickListener { finish() }
    }
}