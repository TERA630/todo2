package com.example.yoshi.todo2

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.yoshi.todo2.databinding.ActivityMainBinding
import org.koin.android.architecture.ext.getViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var vModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        vModel = getViewModel()
        vModel.initItems(this.applicationContext)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.setLifecycleOwner(this@MainActivity)
        binding.handler = vModel
        //　ToDo　ポイント集計ロジック
    }
    override fun onPause() {
        super.onPause()
        val repository = Repository()
        repository.saveListToPreference(vModel.getItemList(), this.applicationContext)
    }
}
