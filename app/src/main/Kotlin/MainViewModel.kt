package com.example.yoshi.todo2

import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    lateinit var titleList: List<String>
    var earnedPoints: Int = 0

    fun initTitleList() {
        titleList = listOf("Wakeup",
                "move the core of body",
                "remember what to do",
                "get up",
                "wash the face",
                "change the cloth",
                "make protein shake",
                "eat and drink",
                "go out",
                "run if I can")
    }


}