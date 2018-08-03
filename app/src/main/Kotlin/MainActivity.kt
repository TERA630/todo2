package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.koin.android.architecture.ext.getViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var vModel: MainViewModel
    private val keyboardUtils: KeyboardUtils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = getViewModel()
        vModel.initItems(this.applicationContext)
        setContentView(R.layout.activity_main)
        keyboardUtils.initHidden(this@MainActivity)
    }
    override fun onPause() {
        super.onPause()
        val repository = Repository()
        repository.saveListToPreference(vModel.getItemList(), this.applicationContext)
    }
}

