# AnimationDemo

# Android高级应用

## 动画基础
### 逐帧动画

* AnimationDrawable
它是一个Drawable的容器，也是Drawable的子类，我们可以把它理解为它事先加载好了一系列的图片，可以被设为视图对象的背景



1. 在drawable中创建loding.xml文件

* <animation-list>这个标签来定义Drawable对象



```
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/frame_1" android:duration="100"/>
    <item android:drawable="@drawable/frame_2" android:duration="100"/>
    <item android:drawable="@drawable/frame_3" android:duration="100"/>
</animation-list>
```



布局代码

```
<Viewandroid:id="@+id/view"
	android:background="@drawable/loading"
	android:layout_centerInParent="true" 
	android:layout_width="300dp" 
	android:layout_height="300dp"/>
```



java代码

```
View view= findViewBvId(R.id.view);
animationDrawable = (AnimationDrawable) view.getBackground();

//开始动画
animationDrawable.start()
//结束动画
animationDrawable.stop()
```



### 视图动画

#### 透明度动画

* Animation类是定义视图对象的抽象类，每一个View都可以展示动画，
* 每一个alpha都对应一个AlphaAnimation对象

1. 在资源文件中新建目录anim文件名为alpha.xml

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <alpha
        android:fromAlpha="1.0"
        android:toAlpha="0.1"
        android:duration="1000"/>
</set>
```



#### 旋转动画

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:fillAfter="true">

    <rotate
        android:fromDegrees="0"
        android:duration="300"
        android:toDegrees="360"
        android:pivotX="50%"
        android:pivotY="50%"
        android:repeatCount="infinite"
        />

</set>
```



#### 缩放动画



```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="1000">
<!--    fillAfter:保持拉伸之后的状态-->
<!--x最开始的比例是1.0也就是不进行缩放，目标值是2.0，也就是要变成原来的2倍-->
<!--   pivotX:基准点x的坐标，默认在左上角-->
    <scale
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="2.0"
        android:toYScale="1.0" />

</set>
```



#### 移动动画

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:fillAfter="true">
    <translate
        android:duration="2000"
        android:fromXDelta="0"
        android:fromYDelta="0"
        android:toXDelta="80%p"
        android:toYDelta="0" />
</set>
```



#### 动画集合

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <rotate
        android:duration="1000"
        android:fromDegrees="0"
        android:toDegrees="720"
        android:pivotX="50%"
        android:pivotY="50%"/>

<!--    android:startOffset="1000"等待1秒后再展示-->
    <translate
        android:duration="1000"
        android:startOffset="1000"
        android:fromXDelta="0"
        android:toXDelta="500"
        android:fromYDelta="0"
        android:toYDelta="0"/>
</set>
```



代码

```
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
```

Animator子类可以调用的方法

```
		anim.setDuration(500);
        // 设置动画运行的时长

        anim.setStartDelay(500);
        // 设置动画延迟播放时间

        anim.setRepeatCount(0);
        // 设置动画重复播放次数 = 重放次数+1
        // 动画播放次数 = infinite时,动画无限重复

        anim.setRepeatMode(ValueAnimator.RESTART);
        // 设置重复播放动画模式
        // ValueAnimator.RESTART(默认):正序重放
        // ValueAnimator.REVERSE:倒序回放

		animator.start();  

```





### 属性动画



#### Valueanimator

```
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
```



#### ObjectAnimator

可以直接控制某个对象的属性

```
ObjectAnimator animator = ObjectAnimator.ofFloat(Object object, String property, float ....values);  
// Object object：需要操作的对象
// String property：需要操作的对象的属性
// float …values：动画初始值 & 结束值（不固定长度）
// 若是两个参数a,b，则动画效果则是从属性的a值到b值
// 若是三个参数a,b,c，则则动画效果则是从属性的a值到b值再到c值
// 以此类推
```

案例：

```
  val animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f)
                animator.setDuration(5000)
                animator.start()
```



#### ViewPropertyAnimator

View自带的方法

```
when(view.id){
	 R.id.button -> {
                view.animate().translationX(500f).setDuration(1000).start()
            }
}
           
```



#### AnimatorSet

动画集合

```
when(view.id){ 

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
```



### 转场动画

#### 揭露动画

布局

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".transition_animation.MainActivity">

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_begin="206dp" />

    <CheckBox
        android:id="@+id/checkBox"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Play animation"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <View

        android:id="@+id/view2"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_marginTop="204dp"
        android:layout_marginEnd="56dp"
        android:background="@color/black"
        android:visibility="invisible"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="544dp"
        android:layout_marginEnd="84dp"

        android:onClick="onClick"
        android:text="Button"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


</androidx.constraintlayout.widget.ConstraintLayout>
```

代码

```
package com.example.animation.transition_animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation.AnimationListener
import android.widget.CheckBox
import androidx.core.animation.addListener
import com.example.animation.R

class MainActivity : AppCompatActivity() {

    lateinit var mView: View
    lateinit var mPlayAnimationCheckBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
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
```





#### 转场动画





创建一个

```
<?xml version="1.0" encoding="utf-8"?>
<transitionSet xmlns:android="http://schemas.android.com/apk/res/android">
    <changeImageTransform android:duration="1000">
        <targets android:targetId="@id/image"/>
    </changeImageTransform>

    <fade android:duration="3000" android:startDelay="1000">

    </fade>
</transitionSet>
```



布局文件

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/second_activity"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".transition_animation.SecondActivity">

    <FrameLayout
        android:id="@+id/scene_root"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
    android:orientation="vertical"
        />
</androidx.constraintlayout.widget.ConstraintLayout>
```

activity_scene布局

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_scene"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".transition_animation.SecondActivity">

    <ImageView
        android:id="@+id/image"
        android:layout_width="160dp"
        android:layout_height="120dp"
        android:src="@drawable/chang_bai" />

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp">

        <ImageButton
            android:id="@+id/btnClose"
            android:transitionName="icon"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentEnd="true"
            android:layout_marginTop="4dp"
            android:background="?android:selectableItemBackground"
            android:src="@drawable/ic_close_black_24dp" />

        <TextView
            android:id="@+id/tvTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_toStartOf="@id/btnClose"
            android:text="title"
            android:textAppearance="@style/TextAppearance.AppCompat.Title" />

        <TextView
            android:id="@+id/tvAddress"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@id/tvTitle"
            android:layout_marginTop="8dp"
            android:text="address"
            android:textAppearance="@style/TextAppearance.AppCompat.Medium" />

        <TextView
            android:id="@+id/tvInfo"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_below="@id/tvAddress"
            android:layout_marginTop="8dp"
            android:text="打发士大夫撒旦开发的时间司搭街坊拉萨搭街坊立刻撒旦解放撒地方立刻集散地立刻反击爱上了打开发到手机发的是看见路上看到九分零四卡德加疯狂拉升的JFK拉萨大家分厘卡时间流口水的房间离开" />

    </RelativeLayout>

</LinearLayout>

```





```
package com.example.animation.transition_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.ViewGroup
import com.example.animation.R

//转场动画
class SecondActivity : AppCompatActivity() {

    lateinit var mInfoScene:Scene
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)



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
```



#### Activity转场

* Fade  淡入
* Side   移入
* Explode  爆炸

```
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


public class FirstActivity extends Activity {

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

```



```
package com.example.animation.activity;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.widget.ImageView;

import com.example.animation.R;


public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        int resId = getIntent().getExtras().getInt("resId");
        ImageView iv = (ImageView) findViewById(R.id.pthoto);
        iv.setTransitionName("img");
        iv.setImageResource(resId);



        Transition transition = new Explode();
        transition.excludeTarget(android.R.id.statusBarBackground,true);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);

    }

}

```

布局

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_first"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"

    tools:context="com.">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/iv1"
            android:layout_width="0dp"
            android:layout_height="160dp"
            android:layout_weight="1"
            android:background="?attr/selectableItemBackground"
            android:onClick="onClick"
            android:src="@drawable/pic1" />

        <ImageView
            android:id="@+id/iv2"
            android:layout_width="0dp"
            android:layout_height="160dp"
            android:layout_weight="1"
            android:background="?attr/selectableItemBackground"
            android:onClick="onClick"
            android:src="@drawable/pic2" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/iv3"
            android:layout_width="0dp"
            android:layout_height="160dp"
            android:layout_weight="1"
            android:background="?attr/selectableItemBackground"
            android:onClick="onClick"
            android:src="@drawable/pic3" />

        <ImageView
            android:id="@+id/iv4"
            android:layout_width="0dp"
            android:layout_height="160dp"
            android:layout_weight="1"
            android:background="?attr/selectableItemBackground"
            android:onClick="onClick"
            android:src="@drawable/pic4" />

    </LinearLayout>

</LinearLayout>

```



```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_second"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    tools:context=".activity.SecondActivity">

    <ImageView
        android:id="@+id/pthoto"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_centerInParent="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>

```



