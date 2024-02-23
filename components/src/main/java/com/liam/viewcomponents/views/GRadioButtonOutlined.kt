package com.liam.viewcomponents.views

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatRadioButton
import com.liam.viewcomponents.R

class GRadioButtonOutlined @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatRadioButton(
    ContextThemeWrapper(context, R.style.GRadioButtonOutlined), attrs, defStyleAttr
) {}