package com.example.yoshi.todo2

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val itemList = MutableLiveData<MutableList<String>>()
    var earnedPoints: Int = 0

    fun initItems() {
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
    }

    fun modifyItem(index: Int, _newString: String) {
        val mList = getItemList()
        mList[index] = _newString
        itemList.value = mList
    }
}
