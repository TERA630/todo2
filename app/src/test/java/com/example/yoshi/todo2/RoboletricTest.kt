package com.example.yoshi.todo2

import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class RobolectricTest {
    @Test
    fun recentDateTest1() {
        val mockedContext = ShadowApplication.getInstance().applicationContext
        val list = fetchRecentDate(mockedContext)
        Assert.assertEquals(list[0], "2018/8/3")
    }
}