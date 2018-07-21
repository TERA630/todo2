package com.example.yoshi.todo2

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context

class MainViewModel : ViewModel() {
    val itemList = MutableLiveData<MutableList<ToDoItem>>()
    var earnedPoints: Int = 0

    fun initItems(_context: Context) {
        val repository = Repository()
        var mList = repository.loadListFromPreference(_context)
        if (mList.equals(mutableListOf(ToDoItem(EMPTY_ITEM)))) {
            val list = listOf("Wakeup",
                    "move the core of body",
                    "remember what to do",
                    "eat and drink",
                    "go out",
                    "run if I can")
            val toDoList = List(6,{index -> ToDoItem(title = list[index])})
            itemList.value = toDoList.toMutableList()
        } else {
            itemList.value = mList.toMutableList()
        }
    }
    fun appendItem(_newString: String) {
        val size = getItemList().size
        val mutableList = itemList.value
        val newItem = ToDoItem(title = "$_newString $size")
        mutableList?.add(size, newItem)
    }
    fun getItemList(): MutableList<ToDoItem> = itemList.value ?: listOf(ToDoItem(EMPTY_ITEM)).toMutableList()

    fun deleteItem(index: Int) {
        val mList = getItemList()
        mList.removeAt(index)
        itemList.value = mList
    }

    fun modifyItem(index: Int, _newString: String) {
        val mList = getItemList()
        mList[index].title = _newString
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
