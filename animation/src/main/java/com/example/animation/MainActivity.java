package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.animation.activity.ActivityTransition;
import com.example.animation.frame.FrameAnimationActivity;
import com.example.animation.attributes.AttributesActivity;
import com.example.animation.reveal.RevealActivity;
import com.example.animation.transition_animation.TransitionActivity;
import com.example.animation.view.ViewAnimationActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button view_animation_button = (Button) findViewById(R.id.view_animation);
        Button attributes_animation_button = (Button) findViewById(R.id.attributes_animation);
        Button frame_animation_button = (Button) findViewById(R.id.frame_animation);
        Button reveal_animation_button = (Button) findViewById(R.id.reveal_animation);
        Button transition_animation_button = (Button) findViewById(R.id.transition_animation);
        Button activity_transition_animation_button = (Button) findViewById(R.id.activity_transition_animation);

        view_animation_button.setOnClickListener(this);
        attributes_animation_button.setOnClickListener(this);
        frame_animation_button.setOnClickListener(this);
        reveal_animation_button.setOnClickListener(this);
        transition_animation_button.setOnClickListener(this);
        activity_transition_animation_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_animation:
                startActivity(new Intent(this, ViewAnimationActivity.class));
                break;

            case R.id.attributes_animation:
                startActivity(new Intent(this, AttributesActivity.class));
                break;
            case R.id.frame_animation:
                startActivity(new Intent(this, FrameAnimationActivity.class));
                break;
            case R.id.reveal_animation:
                startActivity(new Intent(this, RevealActivity.class));
                break;
            case R.id.transition_animation:
                startActivity(new Intent(this, TransitionActivity.class));
                break;
            case R.id.activity_transition_animation:
                startActivity(new Intent(this, ActivityTransition.class));
                break;
        }
    }
}