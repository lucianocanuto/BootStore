package com.btcodans.bootstore.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.btcodans.bootstore.R
import com.btcodans.bootstore.databinding.ActivityMainMenuBinding
import com.btcodans.bootstore.logo.BootStoreLogo

class MainMenuActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainMenuBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        //logo
        binding.txtLogo.text = BootStoreLogo()

    }
}