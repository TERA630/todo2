package com.example.yoshi.todo2

class KeyboardUtils {

    fun hide(context: Context, view: View) {
        imm:InputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)
    }

    fun hide(activity: Activity) {}
    fun initHiden(activity: Activity) {}
    fun show(context: Context, view: View) {}
    fun show(activity: Activity) {}
}