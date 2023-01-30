package com.example.cleanarchitecture.presentation.extention

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Context.showToast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(text: String){
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(@StringRes text: Int){
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Long.convertLongToTime(): String {
    val dateTime = Date(this)
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US)
    return format.format(dateTime)
}

fun String.titlecaseFirstChar() = replaceFirstChar(Char::titlecase)