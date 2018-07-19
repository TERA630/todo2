package com.example.yoshi.todo2

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)

class ExampleInstrumentedTest {
    @Test
    fun calculator() {
        val a = 2
        val b = 2
        assertEquals(4, a + b)
    }

}
