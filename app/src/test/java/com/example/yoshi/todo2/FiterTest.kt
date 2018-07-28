package com.example.yoshi.todo2
import junit.framework.Assert
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Test
import org.junit.runners.Parameterized
import org.robolectric.shadows.ShadowApplication

@RunWith(Parameterized::class)
class ValidTest(val paramOne:String,val paramTwo:Boolean) : Throwable() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name="{0} は　{1} ")
        fun data(): Collection<Array<Any>> = listOf(
                    arrayOf("1999/1/30", true),
                    arrayOf("2018/12/30", true),
                    arrayOf("1789/12/35", false),
                    arrayOf("1999/1/43", false),
                    arrayOf("2018/9/32", false),
                    arrayOf("2018/13/21",false))
    }
    @Test
    fun isValidTest() {
        assertThat(isValidAsDate(paramOne), equalTo(paramTwo))
    }
}
@RunWith(Parameterized::class)
class FilterTest(val paramOne:String,val paramTwo:String,val paramThree:Boolean) : Throwable() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name="{0} と　{1} では　{2}")
        fun data(): Collection<Array<Any>> = listOf(
            arrayOf("1999/1/30", "2018/12/30",true),
            arrayOf("2018/6/30", "2018/6/15",false),
            arrayOf("1000/7/1", "2018/6/30",true),
            arrayOf("1977/6/30", "1977/7/1",true),
            arrayOf("1976/2/3", "1976/3/3",true),
            arrayOf("2018/1/21", "2018/3/321",true))
    }
    @Test
    fun isBeforeTest() {
        assertThat(isBefore(paramOne,paramTwo), equalTo(paramThree))
    }
}
