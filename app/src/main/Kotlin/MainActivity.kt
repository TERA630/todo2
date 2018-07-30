package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.koin.android.architecture.ext.getViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var vModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = getViewModel()
        vModel.initItems(this.applicationContext)
        setContentView(R.layout.activity_main)
        val keyboardUtils = KeyboardUtils()
        keyboardUtils.initHiden(this@MainActivity)
    }
    override fun onPause() {
        super.onPause()
        val repository = Repository()
        repository.saveListToPreference(vModel.getItemList(), this.applicationContext)
    }
}

