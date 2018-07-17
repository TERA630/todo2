package com.example.yoshi.todo2

import android.content.Context

data class todoItem constructor(
        var id: Int = 0,
        var title: String = "thing to do",
        var reward: Int = 1,
        var isRoutain: Boolean = false,
        var hasStartLine: Boolean = false,
        var startLine: String = "----/--/--",
        var hasDeadLine: Boolean = false,
        var deadLine: String = "----/--/--",
        var tagString: String = "home"
)

class DataStorageDeliver {

    fun saveStringToPreference(_key: String, _string: String, context: Context) {
        val preferences = context.getSharedPreferences(_key, Context.MODE_PRIVATE)
        val preferenceEditor = preferences.edit()
        preferenceEditor.putString(_key, _string)
        preferenceEditor.apply()
    }

    fun loadStringFromPreferenece(_key: String, context: Context): String {
        val preferences = context.getSharedPreferences(_key, Context.MODE_PRIVATE)
        return preferences?.getString(_key, "not defined") ?: "fail to load"
    }


}