package com.example.animation.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;

import com.example.animation.R;


public class ActivityTransition extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void onClick(View view) {
        int resId = -1;
        switch (view.getId()) {
            case R.id.iv1:
                resId = R.drawable.pic1;
                break;
            case R.id.iv2:
                resId = R.drawable.pic2;
                break;
            case R.id.iv3:
                resId = R.drawable.pic3;
                break;
            case R.id.iv4:
                resId = R.drawable.pic4;
                break;
        }

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("resId", resId);


        //创建一个Transition对象Explode是透明度的变化
        Transition transition = new Explode();
        //进场，离场动画
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);
        //再次进入的动画
        getWindow().setReenterTransition(transition);

        //第一个参数是被点击的view,第二个transitionName要和第二个页面的setTransitionName一样
        Pair<View, String> shareElement = Pair.create(view, "img");

        //共享元素的切换动画
        getWindow().setSharedElementEnterTransition(transition);


        //让statusBarBackground转场时保持不变
        transition.excludeTarget(android.R.id.statusBarBackground,true);
        //转场对象
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,shareElement);

        startActivity(intent,options.toBundle());
    }
}
