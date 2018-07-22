package com.example.yoshi.todo2

import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.serializer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SerializersTest : Throwable() {

    @Test
    fun intSerializationTest() {
        // List <Int>
            val testIntList = listOf(0, 1, 2, 3, 4)
            val i = IntSerializer
            val li = ArrayListSerializer(i)
            val serializedIntList = JSON.unquoted.stringify(li, testIntList)
            assertEquals("[0,1,2,3,4]", serializedIntList)
            val deSerializedIntList = JSON.unquoted.parse<List<Int>>(li, serializedIntList)
            assertEquals(testIntList, deSerializedIntList)
    }
    @Test
    fun stringSerializationTest() {
        // List <String>
        val testStringList = listOf("dog", "cat", "bird", "fox")
        val s = StringSerializer
        val ls = ArrayListSerializer(s)
        val serializedStringList = JSON.unquoted.stringify(ls, testStringList)
        assertEquals("[dog,cat,bird,fox]", serializedStringList)
        val deserializedStringList = JSON.unquoted.parse<List<String>>(ls, serializedStringList)
        assertEquals(testStringList, deserializedStringList)
    }
    @Test
    fun toDoSerializationTest() {
        // List<toDoItem>
        val testToDoItem = ToDoItem(isRoutine = true, hasStartLine = true, startLine = "2018/7/21")
        val lToDo: KSerializer<ToDoItem> = ToDoItem::class.serializer()
        val serializedToDoItem = JSON.unquoted.stringify(lToDo,testToDoItem)
        val deserializedItem = JSON.unquoted.parse(lToDo,serializedToDoItem)
        assertEquals(testToDoItem,deserializedItem)
    }
    @Test
    fun toDoListSerializationTest() {
        // List<toDoItem>
        val toDoList = List(3,{index -> ToDoItem("item$index")})

        val lToDo: KSerializer<ToDoItem> = ToDoItem::class.serializer()
        val ls = ArrayListSerializer(lToDo)
        val serializedToDoList = JSON.unquoted.stringify(ls,toDoList)
        val deserializedItem = JSON.unquoted.parse(ls,serializedToDoList)
        assertEquals(toDoList,deserializedItem)
    }
}
