/*
package com.giaphat.viewcomponents.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.giaphat.viewcomponents.DisplayName
import com.giaphat.viewcomponents.adapters.GSpinnerAdapter

class GSpinnerTem(context: Context, attrs: AttributeSet? = null) :
    AppCompatSpinner(context, attrs) {

    // Parameters
    var hint: String? = null

init {
        val a: TypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.GDropdownList, 0, 0)
        try {
            hint = a.getString(R.styleable.GDropdownList_dropdown_hint)
        } finally {
            a.recycle()
        }
    }


    fun setItems(items: List<DisplayName>) {
        val list = mutableListOf<DisplayName>()
        hint?.let {
            list.add(GSpinnerAdapter.Hint(it))
        }

        list.addAll(items)

        this.adapter = GSpinnerAdapter(context, list, this.hint)
    }

    fun setOnItemSelectedListener(onItemSelected: (pos: Int) -> Unit) {
        onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                val idx = if (hint == null) pos else pos - 1
                if (idx != -1)
                    onItemSelected(idx)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}

@BindingAdapter("spinner_selected_item")
fun GSpinner.setSelectedItem(position: Int) {
    this.setSelection(position + if (hint != null && adapter.count > 1) 1 else 0)
}

@InverseBindingAdapter(attribute = "spinner_selected_item")
fun GSpinnerTem.getSelectedItemIdx(): Int {
    val idx = selectedItemPosition
    return idx - if (hint != null && adapter.count > 1) 1 else 0
}

@BindingAdapter("spinner_selected_itemAttrChanged")
fun GSpinner.setListeners(
    attrChange: InverseBindingListener
) {
    setOnItemSelectedListener {
        attrChange.onChange()
    }
}
*/
