package com.giaphat.viewcomponents.views

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


//fun Long.toStringDate(): String = DateFormat.format("dd/MM/yyyy", Date(this)).toString()


fun Long.toStringDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date(this))
}
