package com.example.yoshi.todo2

import android.content.Context
import android.util.Log
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.json.JSON

const val ITEM_DATA = "toDoItems"
const val EMPTY_ITEM = "empty item"

@Serializable
data class ToDoItem constructor(
        var title: String = "thing to do",
        var reward: Int = 1,
        var isDone: Boolean = false,
        var isRoutine: Boolean = false,
        var hasStartLine: Boolean = true,
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
            return makeDefaultList(_context)
        } else {
            try {
                val toDoSerializer = ToDoItem::class.serializer()
                val listSerializer = ArrayListSerializer(toDoSerializer)
                return JSON.unquoted.parse(listSerializer, jsonString).toMutableList()
            } catch (e: SerializationException) {
                Log.e("Error", "${e.message} with ${e.cause}")
                return makeDefaultList(_context)
            } catch (e: MissingFieldException) {
                Log.e("Error", e.message)
                return makeDefaultList(_context)
            } catch (e: UnknownFieldException) {
                Log.e("Error", e.message)
                return makeDefaultList(_context)
            }
        }
    }

    private fun makeDefaultList(_context: Context): MutableList<ToDoItem> {
        val res = _context.resources
        val defaultItemTitle = res.getStringArray(R.array.default_todoItem_title)
        val defaultItemStartDate = res.getStringArray(R.array.default_todoItem_startDate)
        val defaultItemTag = res.getStringArray(R.array.default_todoItem_tag)
        val toDoList = List(6, { index -> ToDoItem(title = defaultItemTitle[index], hasStartLine = true, startLine = defaultItemStartDate[index], tagString = defaultItemTag[index]) })
        return toDoList.toMutableList()
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