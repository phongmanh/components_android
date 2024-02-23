package com.liam.viewcomponents.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.fragment.app.FragmentActivity
import com.liam.viewcomponents.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class GDatePicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var textInputLayout: TextInputLayout? = null
    private var textEditInput: TextInputEditText? = null
    private var datePicker: MaterialDatePicker<Long>? = null
    private var fragmentActivity: FragmentActivity? = null
    var hint: String? = null

    init {
        val a: TypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.GInputText, 0, 0)
        try {
            hint = a.getString(R.styleable.GInputText_text_hint)

        } finally {
            a.recycle()
        }

        val rootView: View = inflate(context, R.layout.date_picker_layout, this)
        textInputLayout = rootView.findViewById(R.id.editTextLayout)
        textEditInput = rootView.findViewById(R.id.editTextInput)
        textEditInput?.hint = hint

        fragmentActivity = context as? FragmentActivity

        initialDatePicker()
        textEditInput?.setOnClickListener {
            Log.e("TAG", ":${fragmentActivity == null} ")
            fragmentActivity?.let { datePicker?.show(it.supportFragmentManager, "GDate") }
        }
        textInputLayout?.editText?.doOnTextChanged { text, _, _, _ ->
            setLayoutStyle(text)
        }
        datePicker?.addOnCancelListener { }
        datePicker?.addOnNegativeButtonClickListener {

        }
        datePicker?.addOnPositiveButtonClickListener {
            setText(it.toStringDate())
        }
    }

    fun setHintText(hintText: String) {
        this.hint = hintText
        textEditInput?.hint = hintText
    }

    private fun setLayoutStyle(text: CharSequence?) {
        if (!text.isNullOrBlank()) {
            textInputLayout?.boxBackgroundColor = ContextCompat.getColor(
                context, R.color.colorSelectedBackground
            )

            textInputLayout?.boxStrokeColor = ContextCompat.getColor(
                context, R.color.colorPrimary
            )

        } else {
            textInputLayout?.boxBackgroundColor = ContextCompat.getColor(
                context, R.color.colorWhite
            )
            textInputLayout?.boxStrokeColor = ContextCompat.getColor(
                context, R.color.colorBoxDefault
            )
        }
    }

    private fun initialDatePicker() {
        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText(hint)
        builder.setInputMode(INPUT_MODE_CALENDAR)
        builder.setNegativeButtonText(context.getString(R.string.cancel_label))
        builder.setPositiveButtonText(context.getString(R.string.choice_label))
        datePicker = builder.build()
    }

    fun getText() = textInputLayout?.editText?.text.toString()
    fun setText(text: String) = textInputLayout?.editText?.setText(text)

    fun onTextChangedListener(listener: (text: String) -> Unit) {
        textInputLayout?.editText?.doOnTextChanged { text, _, _, _ ->
            listener(text?.toString() ?: "")
        }
    }

}

@BindingAdapter("dateValue")
fun GDatePicker.setDateValue(text: String) {
    if (text != getText()) this.setText(text)
}

@InverseBindingAdapter(attribute = "dateValue")
fun GDatePicker.getDateValue(): String {
    return this.getText()
}

@BindingAdapter("dateValueAttrChanged")
fun GDatePicker.setListeners(
    attrChange: InverseBindingListener
) {
    onTextChangedListener {
        attrChange.onChange()
    }
}
