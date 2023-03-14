package com.giaphat.viewcomponents.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.giaphat.viewcomponents.R
import com.giaphat.viewcomponents.views.DisplayName


class GSpinnerAdapter(
    context: Context,
    private var values: List<DisplayName>,
    private val hint: String? = null,
    private val getDisplayName: ((DisplayName) -> String)? = null
) : ArrayAdapter<DisplayName>(context, android.R.layout.simple_spinner_dropdown_item, values) {

    override fun getItem(position: Int): DisplayName = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(parent, position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(parent, position)
    }

    private fun createView(parent: ViewGroup, position: Int): View {
        val view = LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getDisplayName?.let {
            it(getItem(position))
        } ?: getItem(position).displayName

        val textColor = if (isEnabled(position)) R.color.colorBlack else R.color.text_grey_1

        textView.setTextColor(ContextCompat.getColor(context, textColor))

        return view
    }


    override fun isEnabled(position: Int): Boolean {
        return !(hint != null && position == 0)
    }

    data class Hint(
        private val hint: String
    ) : DisplayName {

        override val displayName: String
            get() = hint
    }
}
