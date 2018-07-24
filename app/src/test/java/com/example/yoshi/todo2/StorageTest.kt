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
    private lateinit var mockedContext: Context
    @Before
    fun setup() {
        mockedContext = ShadowApplication.getInstance().applicationContext // RuntimeEnvironment.application
        val activity = Robolectric.setupActivity(MainActivity::class.java)

    }
    @Test
    fun test() {
        val testList = mutableListOf<ToDoItem>(ToDoItem("fox"), ToDoItem("dog"), ToDoItem("cat"))
        val repository = Repository()
        repository.saveListToPreference(testList, mockedContext)
        val mList = repository.loadListFromPreference(mockedContext).toMutableList()
        assertEquals(testList, mList)
    }

}
