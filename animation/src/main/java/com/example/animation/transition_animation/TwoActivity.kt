package com.example.animation.transition_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.ViewGroup
import com.example.animation.R

//转场动画
class TwoActivity : AppCompatActivity() {

    lateinit var mInfoScene:Scene
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)



        //framlayout
        val viewGroup = findViewById<ViewGroup>(R.id.scene_root)


        //定义第二个场景
        mInfoScene = Scene.getSceneForLayout(viewGroup,R.layout.scene_info,this)



        //创建一个过度Transition
            val transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.transition)



        //通过TransitionManager我们才能够驱动Transition来进行Scene的过渡动画。
        TransitionManager.go(mInfoScene,transition)

    }






}