package com.example.yoshi.todo2

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        savedInstanceState?.let { viewModel.earnedPoints = it.getInt("earnedPoints") }
        setContentView(R.layout.activity_main)
        achievedPoint.text = "${viewModel.earnedPoints} points"
        //　ToDo　ポイント集計ロジック
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            // ToDo 新規アイテム追加
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.let { it.putInt("earnedPoints", viewModel.earnedPoints) }
    }
}
