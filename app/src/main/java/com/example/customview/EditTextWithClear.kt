package com.example.customview

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText
import androidx.core.content.ContextCompat


class EditTextWithClear @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {


    //从xml中或许属性对象
    private var iconDrawable: Drawable? = null

    init {
        context.theme.obtainStyledAttributes(attrs,R.styleable.EditTextWithClear,0,0)
            .apply {
                try {
                    val iconID = getResourceId(R.styleable.EditTextWithClear_clearIcon,0)
                    if(iconID != 0 ){
                        iconDrawable = ContextCompat.getDrawable(context,iconID)
                    }
                }finally {
                    recycle()
                }
            }

    }

    //当文字改变
    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        toggleClearIcon()
    }


    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { e->
            iconDrawable?.let {
                //当触碰的位置，在图标里的时候
                if(e.action == MotionEvent.ACTION_UP
                    && e.x > width - it.intrinsicWidth
                    && e.x < width +20
                    && e.y > height /2 - it.intrinsicHeight / 2 - 20
                    && e.y <height /2 + it.intrinsicHeight / 2 +20){

                    text?.clear()
                    }


            }
        }
        performClick()
        return super.onTouchEvent(event)
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        toggleClearIcon()
    }
    //显示图标
    private fun toggleClearIcon() {
        val icon = if (isFocused && text?.isNotEmpty() == true ) iconDrawable else null
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,icon,null)


    }
}