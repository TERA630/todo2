package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_makedefault -> Log.i("test", "Item default Selected.")
            R.id.menu_help -> Log.i("test", "Help selected.")
        }
        return true
    }
}

