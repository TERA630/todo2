package com.example.yoshi.todo2

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class KeyboardUtils {
    fun hide(context: Context, view: View) {
        val imm = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
    fun hide(activity: Activity) {
        this.hide(activity, activity.currentFocus)
    }
    fun initHidden(activity: Activity) {
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
    fun show(context: Context, text: EditText) {
        show(context, text)
    }
    fun show(context: Context?, edit: EditText, delayTime: Int) {
        val showKeyboardDelay = Runnable {
            if (context != null) {
                val imm = context
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT)
            }
        }
        Handler().postDelayed(showKeyboardDelay, delayTime.toLong())
    }
}
