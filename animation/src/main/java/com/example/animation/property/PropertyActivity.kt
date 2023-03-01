package com.example.animation.property

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import com.example.animation.R
import kotlin.math.absoluteValue

class PropertyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
    }

    fun onClick(view: View){
        when(view.id){
            R.id.btnValueAnimator ->{
                //valueAnimator是属性动画的引起，可以驱动起始值到终点值属性的变化
                //驱动生成int也可以生成flot
                val valueAnimator = ValueAnimator.ofInt(0, 100)

                //让它匀速运动
                valueAnimator.interpolator = LinearInterpolator()
                //设置变化的过程
                valueAnimator.duration = 100
                valueAnimator.addUpdateListener {

                    //变化完成度
                    val animatedFraction = it.animatedFraction

                    //插值
                    val valueAnimator = it.animatedValue as Int
                    Log.d("HA","onAnimationUpdate: $animatedFraction   ,$valueAnimator")
                }
                //开始动画
                valueAnimator.start()
            }

            //ObjectAnimator，别的动画以此类推
            R.id.button -> {
                val animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f)
                animator.setDuration(5000)
                animator.start()

            }

            //ViewPropertyAnimator
            R.id.button2 -> {
                view.animate().translationX(500f).setDuration(1000).start()
            }

            R.id.button3 -> {
                val rotationAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 720f)
                rotationAnimator.setDuration(1000)

                val xAnimator = ObjectAnimator.ofFloat(view, "x", 0f, 500f)
                xAnimator.setDuration(1000)

                val animatorSet = AnimatorSet()
                //同时播放
//                animatorSet.playTogether(alphaAnimator,xAnimator)

                //按顺序播放
                animatorSet.playSequentially(rotationAnimator,xAnimator)
                animatorSet.start()

                //第二种方式setStartDelay(1000)延迟
                //view.animate().rotation(720f).setDuration(1000).start()
                //view.animate().translationX(500f).setDuration(1000).setStartDelay(1000).start()


            }

        }
    }
}