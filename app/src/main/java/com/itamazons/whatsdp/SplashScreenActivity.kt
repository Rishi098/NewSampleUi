package com.itamazons.teleweb.Activities


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.itamazons.teleweb.R
import com.itamazons.teleweb.databinding.ActivitySplashBinding


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PicAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }

    private fun CircleFade() {
        val animFade: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.circle_fade_in)
        binding.circle.startAnimation(animFade)
        binding.circle.visibility = View.VISIBLE
    }

    private fun PicFadeIn() {
        val animFade: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        binding.qrcode.startAnimation(animFade)
        binding.qrcode.visibility = View.VISIBLE
    }


    private fun PicAnimation() {
        val animZoomOut: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out)
        binding.plan.startAnimation(animZoomOut)

        animZoomOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                PicFadeIn()
                CircleFade()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }


}