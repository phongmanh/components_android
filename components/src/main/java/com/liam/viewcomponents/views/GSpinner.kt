package com.liam.viewcomponents.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.liam.viewcomponents.R
import com.liam.viewcomponents.adapters.GSpinnerAdapter
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class GSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var textInputLayout: TextInputLayout? = null
    private var textEditInput: MaterialAutoCompleteTextView? = null
    private var items = mutableListOf<DisplayName>()

    // Exactly position an item without hint
    private var selectedItemPosition: Int = -1

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

    fun setItems(listItem: List<DisplayName>) {
        hint?.let {
            this.items.add(GSpinnerAdapter.Hint(it))
        }
        this.items.addAll(listItem)
        textEditInput?.setAdapter(GSpinnerAdapter(context, items, this.hint))
    }

    fun setOnItemSelectedListener(onItemSelected: () -> Unit) {

        textEditInput?.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            selectedItemPosition = if (hint == null) position else position - 1
            onItemSelected()
        }
    }

    fun setSelection(pos: Int) {
        pos.takeIf { selectedItemPosition != -1 }?.let {
            val index = if (hint == null) pos else pos + 1
            textEditInput?.setText(items[index].displayName, false)
        }
    }

    fun getSelectedItemPosition(): Int {
        return selectedItemPosition
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