package com.example.yoshi.todo2

import android.content.Context
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class,
        minSdk = 23)

class RobolectricTest {
    private lateinit var mockedContext: Context
    @Before
    fun setup() {
        mockedContext = RuntimeEnvironment.application.applicationContext
    }

    @Test
    fun test() {

    }
}
