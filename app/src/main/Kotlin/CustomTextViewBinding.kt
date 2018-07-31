package com.example.yoshi.todo2

import android.databinding.BindingAdapter
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.TextView

@BindingAdapter("android:checked", "android:text", requireAll = true)
fun CheckBox.setSpannableText(doneChecked: Boolean, _text: String) {
    if (doneChecked) {
        val spannableSir = SpannableString(_text)
        spannableSir.setSpan(StrikethroughSpan(), 0, spannableSir.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.text = spannableSir
        this.isChecked = true
    } else {
        this.text = _text
        this.isChecked = false
    }
}

fun OnEditorDone(txtView: TextView, actionId: Int, event: KeyEvent): Boolean {
    if (actionId != EditorInfo.IME_ACTION_DONE) return false
    Log.i("test", "Doneevent Called")
    return true
}

fun OnFocusChanged(view: View, hasFocus: Boolean) {
    if (!hasFocus) {
        val keyboardUtils = KeyboardUtils()
        keyboardUtils.hide(view.context, view)
    }
}