package com.example.yoshi.todo2

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


class DataStorageDeliverTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.yoshi.todo2", appContext.packageName)
    }

}

@Test
fun saveLoadTest() {
    saveStringToPreference("earnedPoint", "100", )


}