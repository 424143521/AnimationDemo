package com.example.animation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.example.animation.R

class ViewAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation)
    }

    fun onClick(view: View) {
        when(view.id){
            R.id.viewAlphaAnimation ->{
                val animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
                view.startAnimation(animation)
            }

            R.id.viewScaleAnimation ->{
                val animation = AnimationUtils.loadAnimation(this, R.anim.scaie)
                view.startAnimation(animation)
            }
            R.id.viewTranslateAnimation ->{
                val animation = AnimationUtils.loadAnimation(this, R.anim.translate)
                view.startAnimation(animation)
            }
        }

    }
}