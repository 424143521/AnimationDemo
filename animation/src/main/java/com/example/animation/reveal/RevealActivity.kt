package com.example.animation.reveal

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.CheckBox
import com.example.animation.R

class RevealActivity : AppCompatActivity() {

    lateinit var mView: View
    lateinit var mPlayAnimationCheckBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reveal)
        mView = findViewById(R.id.view2)
        mPlayAnimationCheckBox = findViewById(R.id.checkBox)
    }

    fun onClick(view:View){
        val checked = mPlayAnimationCheckBox.isChecked
        when(view.id){
            R.id.button4 -> handleChangeVisibility(checked)
        }

    }

    fun handleChangeVisibility(playAnimation:Boolean){
        //播放动画
        if(playAnimation){
            if(mView.isShown){

                revealExit()
            }else {
                revealEnter()
            }

        } //不播放动画
        else{
            if(mView.isShown){
                mView.visibility = View.INVISIBLE
            }else {
                mView.visibility = View.VISIBLE
            }
        }
    }
    //创建揭露动画
    fun revealEnter(){

        val w = mView.width
        val h = mView.height
        val cx =w/2
        val xy = h/2

        val r = w/2

        //创建圆形的揭露
        val animator =
            ViewAnimationUtils.createCircularReveal(mView, cx, xy, 0f, r.toFloat())

        animator.setDuration(2000)
        mView.visibility = View.VISIBLE

        animator.start()

    }

    //创建揭露离开动画
    fun revealExit(){

        val w = mView.width
        val h = mView.height

        val cx = w
        val cy = h
        val r = Math.hypot(w.toDouble(), h.toDouble())
        val animator =
            ViewAnimationUtils.createCircularReveal(mView, cx, cy, r.toFloat(), 0f)
       //xy为中心点在右下角，第三个参数为启始班级第四个参数为结束半径
        animator.setDuration(5000)

        animator.addListener( object:AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                mView.visibility = View.INVISIBLE
            }
        }
        )
        animator.start()


    }
}