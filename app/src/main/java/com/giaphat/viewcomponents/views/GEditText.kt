package com.giaphat.viewcomponents.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.giaphat.viewcomponents.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class GEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var textInputLayout: TextInputLayout? = null
    private var textEditInput: TextInputEditText? = null
    var hint: String? = null

    init {
        val a: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.GInputText, 0, 0)
        try {
            hint = a.getString(R.styleable.GInputText_text_hint)

        } finally {
            a.recycle()
        }

        val rootView: View = inflate(context, R.layout.component_edit_text_layout, this)
        textInputLayout = rootView.findViewById(R.id.editTextLayout)
        textEditInput = rootView.findViewById(R.id.editTextInput)
        textEditInput?.hint = hint
    }

    fun getText() = textInputLayout?.editText?.text.toString()
    fun setText(text: String) = textInputLayout?.editText?.setText(text)

    fun onTextChangedListener(listener: (text: String) -> Unit) {
        textInputLayout?.editText?.doOnTextChanged { text, _, _, _ ->
            listener(text?.toString() ?: "")
        }
    }

}

@BindingAdapter("textValue")
fun GEditText.setTextValue(text: String) {
    if (text != getText())
        this.setText(text)
}

@InverseBindingAdapter(attribute = "textValue")
fun GEditText.getTextValue(): String {
    return this.getText()
}

@BindingAdapter("textValueAttrChanged")
fun GEditText.setListeners(
    attrChange: InverseBindingListener
) {
    onTextChangedListener {
        attrChange.onChange()
    }
}
