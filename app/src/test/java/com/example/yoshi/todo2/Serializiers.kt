package com.example.yoshi.todo2

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.serializer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@Serializable
data class ToDoItem constructor(
        var id: Int = 0,
        var title: String = "thing to do",
        var reward: Int = 1,
        var isRoutain: Boolean = false,
        var hasStartLine: Boolean = false,
        var startLine: String = "----/--/--",
        var hasDeadLine: Boolean = false,
        var deadLine: String = "----/--/--",
        var tagString: String = "home"
)

@RunWith(JUnit4::class)
class SerializiersTest {
    @Test
    fun serializaitonTest() {
        val i = IntSerializer
        val li = ArrayListSerializer(i)
        val s = StringSerializer
        val ls = ArrayListSerializer(s)
        val toDoItem = ToDoItem()

        val lToDo: KSerializer<ToDoItem> = ToDoItem.serializer()
        val lToDo2: KSerializer<ToDoItem> = ToDoItem::class.serializer()

        val testIntList = listOf(0, 1, 2, 3, 4)
        val testStringList = listOf("dog", "cat", "bird", "fox")

        val serializedIntList = JSON.unquoted.stringify(li, testIntList)
        assertEquals("[0,1,2,3,4]", serializedIntList)
        val deSerializedIntList = JSON.unquoted.parse<List<Int>>(li, serializedIntList)
        assertEquals(testIntList, deSerializedIntList)

        val serializedStringList = JSON.unquoted.stringify(ls, testStringList)

        assertEquals("[dog,cat,bird,fox]", serializedStringList)
        val deserializedStringList = JSON.unquoted.parse<List<String>>(ls, serializedStringList)
        assertEquals(testStringList, deserializedStringList)
    }
}
