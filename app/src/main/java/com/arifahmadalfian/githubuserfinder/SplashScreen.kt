package com.arifahmadalfian.githubuserfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arifahmadalfian.githubuserfinder.databinding.ActivitySplashScreenBinding
import com.arifahmadalfian.githubuserfinder.main.MainActivity

class SplashScreen : AppCompatActivity() {

    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        with(binding) {
            this?.ivSplashScreen?.alpha = 0f
            this?.ivSplashScreen?.animate()?.setDuration(1500)?.alpha(1f)?.withEndAction {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}