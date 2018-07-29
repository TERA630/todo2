package com.example.yoshi.todo2

import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication
import org.robolectric.Robolectric



@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 23)
class RobolectricTest {
    @Test
    fun test() {
        val mockedContext = ShadowApplication.getInstance().applicationContext // RuntimeEnvironment.application
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val res = mockedContext.resources
        val defaultItemTitle = res.getStringArray(R.array.default_todoItem)
        val toDoList = List(6,{index -> ToDoItem(title = defaultItemTitle[index])})
    }

}
