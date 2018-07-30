package com.example.yoshi.todo2

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