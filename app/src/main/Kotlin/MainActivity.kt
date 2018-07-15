package com.example.yoshi.todo2

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vModel = getViewModel<MainViewModel>()
        Log.i("test", "$vModel")
        //　ToDo　ポイント集計ロジック
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            // ToDo 新規アイテム追加
        }
    }
}
