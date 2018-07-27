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
class FilterTest(val paramOne:String,val paramTwo:Boolean) : Throwable() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                    arrayOf("1999/1/30", true),
                    arrayOf("2018/12/30", true),
                    arrayOf("1789/12/35", false),
                    arrayOf("1999/1/43", false),
                    arrayOf("2018/9/32", false),
                    arrayOf("2018/13/21",false))
        }
    }

    @Test
    fun isValidTest() {
        assertThat(isValidAsDate(paramOne), equalTo(paramTwo))
    }
}

fun isValidAsDate(_string:String) : Boolean{
    val validCheckEx = Regex("""([12]\d{3})/(\d|1[012])/(\d|[12][0-9]|3[01])""")
    return validCheckEx.matches(_string)
}

fun isAfter (targetDate:String, baseDate:String) :Boolean {
    val validCheckEx = Regex("""([12][0-9]{3})/([0-9]|1[012])/([0-9]|[12][0-9]|3[01])""")
    if(!( (validCheckEx.matches(targetDate)) && (validCheckEx.matches(baseDate))) ){ throw Exception("your $targetDate or $baseDate illegal dateString")}
    val getDateEx = Regex("""([12]\d{3})/(\d|1[012])/(\d|[12]\d|3[01])""")

    val matchingTargetDate = getDateEx.find(targetDate)
    val (targetYear,targetMonth,targetDay) = matchingTargetDate!!.destructured
    val matchingbaseDate = getDateEx.find(baseDate)
    val (baseYear,baseMonth,baseDay) = matchingTargetDate!!.destructured
    when {
        ( targetYear > baseYear) -> { return true }
        ( targetYear < baseYear) -> { return false}
        ( targetYear == baseYear) -> {
            when {
                (targetMonth > baseMonth) -> {return true}
                (targetMonth < baseMonth) -> {return false}
                (targetMonth == baseMonth) -> {
                    when {
                        (targetDate > baseDay) -> {return true}
                        (targetDate <=  baseDay) -> {return false}
                    }
                }
            }
        }
    }
    return false
}
