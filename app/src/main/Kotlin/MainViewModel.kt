package com.example.yoshi.todo2

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.res.Resources

class MainViewModel : ViewModel() {
    val itemList = MutableLiveData<MutableList<ToDoItem>>()
    var earnedPoints: Int = 0

    fun initItems(_context: Context) {
        val repository = Repository()
        var mList = repository.loadListFromPreference(_context)
        if (mList.equals(mutableListOf(ToDoItem(EMPTY_ITEM)))) {
            val res = _context.resources
            val defaultItemTitle = res.getStringArray(R.array.default_todoItem)
            val toDoList = List(6,{index -> ToDoItem(title = defaultItemTitle[index])})
            itemList.value = toDoList.toMutableList()
        } else {
            itemList.value = mList.toMutableList()
        }
    }
    fun appendItem(newItem: ToDoItem) {
        val size = getItemList().size
        val mList = getItemList()
        mList.add(size, newItem)
        itemList.value = mList
    }
    fun getItemList(): MutableList<ToDoItem> = itemList.value ?: listOf(ToDoItem(EMPTY_ITEM)).toMutableList()
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
}
