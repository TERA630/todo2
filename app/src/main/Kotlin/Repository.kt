package com.example.yoshi.todo2

import android.content.Context
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.JSON


const val ITEM_DATA = "toDoItems"
const val EMPTY_ITEM = "empty item"

class Repository {

    fun listToJSON(_mutableList: MutableList<String>) {
        val json: String = JSON.stringify(_mutableList)
    }

    fun saveStringToPreference(_key: String, _string: String, context: Context) {
        val preferences = context.getSharedPreferences(_key, Context.MODE_PRIVATE)
        val preferenceEditor = preferences.edit()
        preferenceEditor.putString(_key, _string)
        preferenceEditor.apply()
    }

    fun loadStringFromPreference(_key: String, context: Context): String {
        val preferences = context.getSharedPreferences(_key, Context.MODE_PRIVATE)
        return preferences?.getString(_key, EMPTY_ITEM) ?: EMPTY_ITEM
    }

    fun saveListToPreference(_mList: MutableList<String>, _context: Context) {
        val ListStringSerializer = ArrayListSerializer(StringSerializer)
        val serializedStringList = JSON.unquoted.stringify(ListStringSerializer, _mList.toList())
        saveStringToPreference(ITEM_DATA, serializedStringList, _context)
    }

    fun loadListFromPreference(_context: Context): MutableList<String> {
        val jsonString = loadStringFromPreference(ITEM_DATA, _context)
        val ListStringSerializer = ArrayListSerializer(StringSerializer)
        if (jsonString == EMPTY_ITEM) {
            return mutableListOf("$EMPTY_ITEM")
        } else {
            return JSON.unquoted.parse(ListStringSerializer, jsonString).toMutableList()
        }
    }
}