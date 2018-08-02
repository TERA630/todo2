package com.example.yoshi.todo2

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.serializer

const val ITEM_DATA = "toDoItems"
const val EMPTY_ITEM = "empty item"

@Serializable
data class ToDoItem constructor(
        var title: String = "thing to do",
        var reward: Int = 1,
        var isDone: Boolean = false,
        var isRoutine: Boolean = false,
        var hasStartLine: Boolean = false,
        var startLine: String = "----/--/--",
        var hasDeadLine: Boolean = false,
        var deadLine: String = "----/--/--",
        var tagString: String = "home"
)

class FilteredToDoItem constructor(
        var unFilter: Int = 0,
        var item: ToDoItem = ToDoItem()
)

class Repository {
    private fun saveStringToPreference(_key: String, _string: String, context: Context) {
        val preferences = context.getSharedPreferences(_key, Context.MODE_PRIVATE)
        val preferenceEditor = preferences.edit()
        preferenceEditor.putString(_key, _string)
        preferenceEditor.apply()
    }

    private fun loadStringFromPreference(_key: String, context: Context): String {
        val preferences = context.getSharedPreferences(_key, Context.MODE_PRIVATE)
        return preferences?.getString(_key, EMPTY_ITEM) ?: EMPTY_ITEM
    }

    fun saveListToPreference(_mList: MutableList<ToDoItem>, _context: Context) {
        val toDoSerializer = ToDoItem::class.serializer()
        val listSerializer = ArrayListSerializer(toDoSerializer)
        val serializedStringList = JSON.unquoted.stringify(listSerializer, _mList.toList())
        saveStringToPreference(ITEM_DATA, serializedStringList, _context)
    }

    fun loadListFromPreference(_context: Context): MutableList<ToDoItem> {
        val jsonString = loadStringFromPreference(ITEM_DATA, _context)
        if (jsonString == EMPTY_ITEM) {
            return mutableListOf(ToDoItem("$EMPTY_ITEM"))
        } else {
            val toDoSerializer = ToDoItem::class.serializer()
            val listSerializer = ArrayListSerializer(toDoSerializer)
            return JSON.unquoted.parse(listSerializer, jsonString).toMutableList()
        }
    }
}

fun MutableList<FilteredToDoItem>.swap(oneIndex: Int, otherIndex: Int) {
    val temp = this[otherIndex]
    this[otherIndex] = this[oneIndex]
    this[oneIndex] = temp
}

fun MutableList<FilteredToDoItem>.filterByDate(dateStr: String): MutableList<FilteredToDoItem> {
    val resultlist = emptyList<FilteredToDoItem>().toMutableList()
    for (i in this.indices) {
        if ((this[i].item.hasStartLine) && isBefore(dateStr, this[i].item.startLine)) {
            resultlist.add(FilteredToDoItem(i, this[i].item))
        }
    }
    return resultlist
}