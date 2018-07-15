package com.example.yoshi.todo2

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import androidx.navigation.NavOptions

class MainViewModel : ViewModel() {
    val titleList: List<String> = emptyList()
    var earnedPoints: Int = 0
    lateinit var navOptions: NavOptions

    fun initNavAnim() {
        navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_bottom)
                .setExitAnim(R.anim.slide_out_top)
                .setPopEnterAnim(R.anim.slide_in_top)
                .setPopExitAnim(R.anim.slide_out_bottom)
                .build()
    }

}