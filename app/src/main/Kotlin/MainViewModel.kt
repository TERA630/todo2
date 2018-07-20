package com.example.yoshi.todo2

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context

class MainViewModel : ViewModel() {
    val itemList = MutableLiveData<MutableList<String>>()
    var earnedPoints: Int = 0

    fun initItems(_context: Context) {
        val repository = Repository()
        var mList = repository.loadListFromPreference(_context)
        if (mList.equals(mutableListOf(EMPTY_ITEM))) {
            val list = listOf("Wakeup",
                    "move the core of body",
                    "remember what to do",
                    "get up",
                    "wash the face",
                    "change the cloth",
                    "make protein shake",
                    "eat and drink",
                    "go out",
                    "run if I can")
            itemList.value = list.toMutableList()
        } else {
            itemList.value = mList
        }
    }
    fun appendItem(_newString: String) {
        val size = getItemList().size
        val mutableList = itemList.value
        mutableList?.add(size + 1, _newString)
    }

    fun getItemList(): MutableList<String> = itemList.value ?: listOf("not defined").toMutableList()

    fun deleteItem(index: Int) {
        val mList = getItemList()
        mList.removeAt(index)
        itemList.value = mList
    }

    fun modifyItem(index: Int, _newString: String) {
        val mList = getItemList()
        mList[index] = _newString
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
