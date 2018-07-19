package com.example.yoshi.todo2

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.JSON

class DataStorageDeliver {

    fun listToJSON(_mutableList: MutableList<String>) {

        val json: String = JSON.stringify(_mutableList)
        Log.i("test", "$json in listtoJSON: ")

    }
    fun saveStringToPreference(_key: String, _string: String, context: Context) {
        val preferences = context.getSharedPreferences(_key, Context.MODE_PRIVATE)
        val preferenceEditor = preferences.edit()
        preferenceEditor.putString(_key, _string)
        preferenceEditor.apply()
    }

    fun loadStringFromPreference(_key: String, context: Context): String {
        val preferences = context.getSharedPreferences(_key, Context.MODE_PRIVATE)
        return preferences?.getString(_key, "not defined") ?: "fail to load"
    }


}