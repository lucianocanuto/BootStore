package com.btcodans.bootstore.ui

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import com.btcodans.bootstore.databinding.ActivitySplashBinding
import com.btcodans.bootstore.logo.BootStoreLogo


class SplashActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // aplicação do logo
        binding.txtLogoSplash.text = BootStoreLogo()
        binding.txtLogoSplash.alpha = 0f
        binding.txtLogoSplash.animate().alpha(1f).setDuration(2000).start()

        // tempo de espera e navega para o menu
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainMenuActivity::class.java))
            finish()
        }, 3000)

    }
}