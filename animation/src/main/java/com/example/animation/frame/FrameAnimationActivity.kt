package com.example.animation.frame

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.animation.R

class FrameAnimationActivity : AppCompatActivity() {

    //创建Drawable容器
    lateinit var animationDrawable: AnimationDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)

        val view = findViewById<View>(R.id.view)
        animationDrawable = view.background as AnimationDrawable
        val start = findViewById<Button>(R.id.BtnStart)
        val stop = findViewById<Button>(R.id.BtnStop)
        start.setOnClickListener {
            animationDrawable.start()
        }

        stop.setOnClickListener {
            animationDrawable.stop()
        }
        //通常情况下它会循环往复的执行动画只执行一遍
        animationDrawable.isOneShot = true

    }


}