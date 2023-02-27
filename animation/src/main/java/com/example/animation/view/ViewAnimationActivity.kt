package com.example.animation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.example.animation.R

class ViewAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.renew,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.renew -> recreate()
        }
        return super.onOptionsItemSelected(item)
    }
    fun onClick(view: View) {
        when(view.id){
            R.id.viewAlphaAnimation ->{
                val animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
                view.startAnimation(animation)
            }

            R.id.viewScaleAnimation ->{
                val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
                view.startAnimation(animation)
            }
            R.id.viewTranslateAnimation ->{
                val animation = AnimationUtils.loadAnimation(this, R.anim.translate)
                view.startAnimation(animation)
            }
            R.id.viewRotateAnimation ->{
                val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
                view.startAnimation(animation)
            }
            R.id.viewSetAnimation ->{
                val animation = AnimationUtils.loadAnimation(this, R.anim.set)
                view.startAnimation(animation)
            }
            R.id.viewLinear,R.id.viewAccelerate ->{
                val viewLinear = findViewById<View>(R.id.viewLinear)
                val viewAccelerate = findViewById<View>(R.id.viewAccelerate)

                val animationLinear = AnimationUtils.loadAnimation(this, R.anim.translate)
                val animationAccelerate = AnimationUtils.loadAnimation(this, R.anim.translate)

                //创建变化率
                animationLinear.interpolator = LinearInterpolator()
                animationAccelerate.interpolator = AccelerateInterpolator()



                viewLinear.startAnimation(animationLinear)
                viewAccelerate.startAnimation(animationAccelerate)


            }
        }

    }
}