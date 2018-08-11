package com.example.yoshi.todo2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
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
        val myDrawer = main_drawer
        val mItems = this.resources.getStringArray(R.array.menu_items)
        val drawerList = drawer_contents
        val menuListAdapter = ArrayAdapter<String>(this, R.layout.list_items, mItems)
        drawerList.adapter = (menuListAdapter)


    }
    override fun onPause() {
        super.onPause()
        val repository = Repository()
        repository.saveListToPreference(vModel.getItemList(), this.applicationContext)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_makedefault -> Log.i("test", "Item default Selected.")
            R.id.menu_help -> Log.i("test", "Help selected.")
        }
        return true
    }
}

