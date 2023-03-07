package com.giaphat.viewcomponents.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.giaphat.viewcomponents.DisplayName
import com.giaphat.viewcomponents.R
import com.giaphat.viewcomponents.adapters.GSpinnerAdapter
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class GSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var textInputLayout: TextInputLayout? = null
    private var textEditInput: MaterialAutoCompleteTextView? = null

    var hint: String? = null

    init {

        val a: TypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.GInputText, 0, 0)
        try {
            hint = a.getString(R.styleable.GInputText_text_hint)

        } finally {
            a.recycle()
        }

        val rootView: View = inflate(context, R.layout.component_spinner_layout, this)
        textInputLayout = rootView.findViewById(R.id.spinnerLayout)
        textEditInput = rootView.findViewById(R.id.spinnerInput)
        textEditInput?.hint = hint
    }

    fun setItems(items: List<DisplayName>) {
        val list = mutableListOf<DisplayName>()
        hint?.let {
            list.add(GSpinnerAdapter.Hint(it))
        }
        list.addAll(items)
        textEditInput?.setAdapter(GSpinnerAdapter(context, list, this.hint))
    }

    fun setOnItemSelectedListener(onItemSelected: (pos: Int) -> Unit) {

        textEditInput?.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val idx = if (hint == null) position else position - 1
                if (idx != -1)
                    onItemSelected(idx)
            }
    }

    fun setSelection(pos: Int) {
        textEditInput?.setSelection(pos)
        /*   textEditInput?.let {
               val value = it.adapter.getItem(pos) as DisplayName
               it.setText(value.displayName)
           }*/
    }

    fun getSelectedItemPosition(): Int {
        val idx = textEditInput?.listSelection ?: -1
        return idx - if (hint != null && textEditInput?.adapter?.count!! > 1) 1 else 0
    }
}

@BindingAdapter("spinnerItemSelected")
fun GSpinner.setSelectedItem(position: Int) {
    setSelection(position)
}

@InverseBindingAdapter(attribute = "spinnerItemSelected")
fun GSpinner.getSelectedItemIdx(): Int {
    return getSelectedItemPosition()
}

@BindingAdapter("spinnerItemSelectedAttrChanged")
fun GSpinner.setListeners(
    attrChange: InverseBindingListener
) {
    setOnItemSelectedListener {
        attrChange.onChange()
    }
}