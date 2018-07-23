package com.example.yoshi.todo2

import android.databinding.BindingAdapter
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.util.Log
import android.widget.TextView

class CustomTextViewBinding {

    companion object {
        @JvmStatic
        @BindingAdapter("app:done", "android:text")
        fun setSpannableText(view: TextView, doneChecked: Boolean, _text: String) {
            Log.i("test", "$doneChecked with $_text:setSpannableText")

            if (doneChecked) {
                val spannableSir = SpannableString(_text)
                spannableSir.setSpan(StrikethroughSpan(), 0, spannableSir.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                view.text = spannableSir
            } else {
                view.text = _text
            }
        }
    }


}