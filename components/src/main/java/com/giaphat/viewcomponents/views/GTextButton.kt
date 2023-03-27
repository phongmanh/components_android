package com.giaphat.viewcomponents.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton
import com.giaphat.viewcomponents.R

class GTextButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0
) : AppCompatButton(
    ContextThemeWrapper(
        context,
        R.style.GTextButtonPrimary
    ), attrs, defStyle
)