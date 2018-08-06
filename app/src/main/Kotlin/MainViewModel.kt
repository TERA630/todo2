package com.example.yoshi.todo2

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.TextView

class MainViewModel : ViewModel() {
    val itemList = MutableLiveData<MutableList<ToDoItem>>()
    var earnedPoints: Int = 0
    lateinit var filterSpinnerStrList: MutableList<String>
    lateinit var currentDateStr: String
    var currentTagFilter: CharSequence = ""

    fun initItems(_context: Context) {
        val repository = Repository()
        itemList.value = repository.loadListFromPreference(_context)
        filterSpinnerStrList = fetchRecentDate(context = _context)
        currentDateStr = filterSpinnerStrList[0]
    }
    fun appendItem(newItem: ToDoItem) {
        val size = getItemList().size
        val mList = getItemList()
        mList.add(size, newItem)
        itemList.value = mList
    }
    fun deleteItem(index: Int) {
        val mList = getItemList()
        mList.removeAt(index)
        itemList.value = mList
    }
    fun swapItem(fromPosition: Int, toPosition: Int) {
        val list = getItemList()
        val str = list[toPosition]
        list[toPosition] = list[fromPosition]
        list[fromPosition] = str
        itemList.value = list
    }
    fun getItemList(): MutableList<ToDoItem> = itemList.value
            ?: listOf(ToDoItem(EMPTY_ITEM)).toMutableList()

    fun getItemListCurrentWithTag(targetDate: String, filterStr: CharSequence): MutableList<FilteredToDoItem> {
        val resultList = getItemListWithTag(filterStr.toString()).filterByDate(targetDate)
        Log.i("test", " ${resultList.size} of item was got ")
        return resultList
    }

    fun getItemListWithTag(filterStr: CharSequence): MutableList<FilteredToDoItem> {
        val rawList = getItemList()
        val regFilterStr = Regex(filterStr.toString())
        var filteredList: MutableList<FilteredToDoItem> = emptyList<FilteredToDoItem>().toMutableList()
        for (i in 0..rawList.lastIndex) {
            if (rawList[i].tagString.contains(regFilterStr)) {
                filteredList.add(FilteredToDoItem(i, rawList[i].copy()))
            }
        }
        return filteredList
    }
    /* fun getItemListWithDate(targetDate:String):MutableList<FilteredToDoItem>{
         val rawList = getItemList()
         val recentDates = fetchRecentDate()
         return
     }*/
    fun onEditorActionDone(edit: TextView, actionId: Int, event: KeyEvent?): Boolean {
        Log.i("test", "onEditorActionDone Call")
        when (actionId) {
            EditorInfo.IME_ACTION_DONE, EditorInfo.IME_ACTION_NONE, EditorInfo.IME_ACTION_NEXT, EditorInfo.IME_NULL -> {
                currentTagFilter = edit.text ?: ""
                val keyboardUtils = KeyboardUtils()
                keyboardUtils.hide(edit.context, edit)
                return true
            }
            else -> {
                return false
            }
        }
    }
    fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        Log.i("test", "spinner ${position}selected")
        currentDateStr = filterSpinnerStrList[position]

    }
}
