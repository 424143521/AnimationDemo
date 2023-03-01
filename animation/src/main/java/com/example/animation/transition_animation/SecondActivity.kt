package com.example.animation.transition_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.View
import android.view.View.OnClickListener
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.animation.R

//转场动画
class SecondActivity : AppCompatActivity(),OnClickListener {

    lateinit var mOverViewScene:Scene
    lateinit var mInfoScene:Scene
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val viewGroup = findViewById<ViewGroup>(R.id.scene_root)
        val view = inflate(this, R.layout.scene_overview, null);
        val button = view.findViewById<Button>(R.id.btnInfo)
        button.setOnClickListener {
            val transition = TransitionInflater.from(getBaseContext())
                .inflateTransition(R.transition.transition)
            TransitionManager.go(mInfoScene, transition)
        }
        //定义第一个场景
        mOverViewScene =
            Scene.getSceneForLayout(viewGroup, R.layout.scene_overview, baseContext)

        //定义第二个场景
        mInfoScene = Scene.getSceneForLayout(viewGroup,R.layout.scene_info,baseContext)

        TransitionManager.go(mOverViewScene)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnInfo -> {

            }
            R.id.btnClose -> TransitionManager.go(mOverViewScene)
        }
    }




}