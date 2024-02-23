package com.liam.viewcomponents.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton
import com.liam.viewcomponents.R

class GButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatButton(ContextThemeWrapper(context, R.style.GButtonPrimary), attrs, defStyleAttr) {
}