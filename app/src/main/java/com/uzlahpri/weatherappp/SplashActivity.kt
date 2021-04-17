package com.uzlahpri.weatherappp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.uzlahpri.weatherappp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        val animCloud = AnimationUtils.loadAnimation(this, R.anim.splash_code)
        val animSun = AnimationUtils.loadAnimation(this, R.anim.splash_sun)
        binding.ivCloudSplash.animation = animCloud
        binding.ivSunSplash.animation = animSun

        object : CountDownTimer(3600, 200){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(MainActivity.getLaunchService(this@SplashActivity))
            }

        }.start()

        super.onResume()
    }
}