package com.example.yoshi.todo2

import android.content.Context
import java.util.*

fun isValidAsDate(_string: String): Boolean {
    val validCheckEx = Regex("""([12]\d{3})/(\d|1[012])/(\d|[12][0-9]|3[01])""")
    return validCheckEx.matches(_string)
}
fun isAfter(targetDateStr: String, baseDateStr: String): Boolean {
    val dateRegEx = Regex("""([12]\d{3})/(\d|1[012])/(\d|[12][0-9]|3[01])""")
    if (!((dateRegEx.matches(targetDateStr)) && (dateRegEx.matches(baseDateStr)))) {
        throw Exception("your $targetDateStr or $baseDateStr illegal dateString")
    }
    val matchingTargetDate = dateRegEx.find(targetDateStr)
    val (targetYear, targetMonth, targetDay) = matchingTargetDate!!.destructured
    val matchingBaseDate = dateRegEx.find(baseDateStr)
    val (baseYear, baseMonth, baseDay) = matchingBaseDate!!.destructured
    when {
        (targetYear > baseYear) -> return true
        (targetYear < baseYear) -> return false
        (targetYear == baseYear) -> {
            when {
                (targetMonth > baseMonth) -> return true
                (targetMonth < baseMonth) -> return false
                (targetMonth == baseMonth) -> {
                    when {
                        (targetDay >= baseDay) -> return true
                        (targetDay < baseDay) -> return false
                    }
                }
            }
        }
    }
    return false
}
fun isBefore(targetDateStr: String, baseDateStr: String): Boolean {
    val dateRegEx = Regex("""([12]\d{3})/(\d|1[012])/(\d|[12][0-9]|3[01])""")
    if (!((dateRegEx.matches(targetDateStr)) && (dateRegEx.matches(baseDateStr)))) {
        throw Exception("your $targetDateStr or $baseDateStr illegal dateString")
    }
    val matchingTargetDate = dateRegEx.find(targetDateStr)
    val (targetYear, targetMonth, targetDay) = matchingTargetDate!!.destructured
    val matchingBaseDate = dateRegEx.find(baseDateStr)
    val (baseYear, baseMonth, baseDay) = matchingBaseDate!!.destructured
    when {
        (targetYear < baseYear) -> return true
        (targetYear > baseYear) -> return false
        (targetYear == baseYear) -> {
            when {
                (targetMonth < baseMonth) -> return true
                (targetMonth > baseMonth) -> return false
                (targetMonth == baseMonth) -> {
                    when {
                        (targetDay <= baseDay) -> return true
                        (targetDay > baseDay) -> return false
                    }
                }
            }
        }
    }
    return false
}

fun fetchRecentDate(context: Context): MutableList<String> {
    val resultList = MutableList(6) { "" }
    val filterIntList = context.resources.getIntArray(R.array.dateFilter_array)
    // 当日､翌日､2日後､3日後,7日後､30日後
    for (i in filterIntList.indices) {
        val cal = Calendar.getInstance(Locale.JAPAN)
        cal.add(Calendar.DATE, filterIntList[i])
        resultList[i] = "${cal.get(Calendar.YEAR)}/${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.DAY_OF_MONTH)}"
    }
    return resultList
}