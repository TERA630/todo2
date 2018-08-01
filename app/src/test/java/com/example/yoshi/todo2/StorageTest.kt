package com.example.yoshi.todo2

import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 23)
class RobolectricTest {
    @Test
    fun test() {
        val list = fetchRecentDate()
        Assert.assertEquals(list[0], "2018/8/1")
    }
}