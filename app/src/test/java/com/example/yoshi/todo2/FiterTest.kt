package com.example.yoshi.todo2

import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ValidTest(val paramOne: String, val paramTwo: Boolean) : Throwable() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0} は　{1} ")
        fun data(): Collection<Array<Any>> = listOf(
                arrayOf("1999/1/30", true),
                arrayOf("2018/12/30", true),
                arrayOf("1789/12/35", false),
                arrayOf("1999/1/43", false),
                arrayOf("2018/9/32", false),
                arrayOf("2018/13/21", false))
    }

    @Test
    fun isValidTest() {
        assertThat(isValidAsDate(paramOne), equalTo(paramTwo))
    }
}

@RunWith(Parameterized::class)
class fetchdateTest(val paramOne: Int, val paramTwo: String, paramThree: Boolean) : Throwable() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0} は　{1} ")
        fun data(): Collection<Array<Any>> = listOf(
                arrayOf(0, "2018/8/1", true),
                arrayOf(1, "2018/8/2", true),
                arrayOf(2, "2018/8/3", true),
                arrayOf(3, "2018/8/4", true),
                arrayOf(4, "2018/8/5", true),
                arrayOf(5, "2018/8/6", true),
                arrayOf(6, "2018/8/7", true))
    }
    @Test
    fun dateFetchTest() {
        val list = fetchRecentDate()
        assertThat(list[paramOne], Matchers.equalTo(paramTwo))
    }
}
