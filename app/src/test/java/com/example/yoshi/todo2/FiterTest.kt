package com.example.yoshi.todo2

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class TestWithParameter(val paramOne: String, val paramTwo: String, val paramThree: Boolean) : Throwable() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0} は　{1} ")
        fun data(): Collection<Array<Any>> = listOf(
                arrayOf("1999/1/30", "2018/12/30", false),
                arrayOf("2018/6/30", "2018/6/15", true),
                arrayOf("2018/7/1", "2018/6/30", true),
                arrayOf("1977/6/30", "1977/7/1", false),
                arrayOf("1976/2/3", "1976/3/3", false),
                arrayOf("1999/1/30", "2018/12/30", true),
                arrayOf("2018/6/30", "2018/6/15", false),
                arrayOf("1000/7/1", "2018/6/30", true),
                arrayOf("1977/6/30", "1977/7/1", true),
                arrayOf("1976/2/3", "1976/3/3", true),
                arrayOf("2018/1/21", "2018/3/321", true))
    }
    @Test
    fun isBeforeTest() {
        assertThat(isBefore(paramOne, paramTwo), equalTo(paramThree))
    }
}
