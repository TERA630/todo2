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
            arrayOf("1999/1/30", "2018/12/30",false),
            arrayOf("2018/6/30", "2018/6/15",true),
            arrayOf("2018/7/1", "2018/6/30",true),
            arrayOf("1977/6/30", "1977/7/1",false),
            arrayOf("1976/2/3", "1976/3/3",false),
            arrayOf("2018/1/21", "2018/3/21",false))
    }

    @Test
    fun isValidTest() {
        assertThat(isAfter(paramOne,paramTwo), equalTo(paramThree))
    }
}

fun isValidAsDate(_string:String) : Boolean{
    val validCheckEx = Regex("""([12]\d{3})/(\d|1[012])/(\d|[12][0-9]|3[01])""")
    return validCheckEx.matches(_string)
}
fun isAfter (targetDateStr:String, baseDateStr:String) :Boolean {
    val dateRegEx = Regex("""([12]\d{3})/(\d|1[012])/(\d|[12][0-9]|3[01])""")
    if(!( (dateRegEx.matches(targetDateStr)) && (dateRegEx.matches(baseDateStr))) ){ throw Exception("your $targetDateStr or $baseDateStr illegal dateString")}
    val matchingTargetDate = dateRegEx.find(targetDateStr)
    val (targetYear,targetMonth,targetDay) = matchingTargetDate!!.destructured
    val matchingBaseDate = dateRegEx.find(baseDateStr)
    val (baseYear,baseMonth,baseDay) = matchingBaseDate!!.destructured
    when {
        ( targetYear > baseYear) -> return true
        ( targetYear < baseYear) -> return false
        ( targetYear == baseYear) -> {
            when {
                (targetMonth > baseMonth) -> return true
                (targetMonth < baseMonth) -> return false
                (targetMonth == baseMonth) -> {
                    when {
                        (targetDay > baseDay) -> return true
                        (targetDay <=  baseDay) -> return false
                    }
                }
            }
        }
    }
    return false
}
