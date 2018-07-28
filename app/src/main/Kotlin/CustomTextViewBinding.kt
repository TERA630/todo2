package com.example.yoshi.todo2

import android.databinding.BindingAdapter
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.util.Log
import android.widget.CheckBox

@BindingAdapter("android:checked", "android:text", requireAll = true)
fun CheckBox.setSpannableText(doneChecked: Boolean, _text: String) {
    Log.i("test", "$doneChecked with $_text:setSpannableText")
    if (doneChecked) {
        val spannableSir = SpannableString(_text)
        spannableSir.setSpan(StrikethroughSpan(), 0, spannableSir.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.text = spannableSir
    } else {
        this.text = _text
    }
}