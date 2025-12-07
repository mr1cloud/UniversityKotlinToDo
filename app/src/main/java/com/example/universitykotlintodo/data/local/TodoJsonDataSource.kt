package com.example.universitykotlintodo.data.local

import android.content.Context
import com.example.universitykotlintodo.data.model.TodoItemDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TodoJsonDataSource(private val context: Context) {
    private val gson = Gson()
    fun getTodos(): List<TodoItemDto> {
        val json = context.assets.open("todos.json").bufferedReader().use {
            it.readText()
        }
        val type = object : TypeToken<List<TodoItemDto>>() {}.type
        return gson.fromJson(json, type)
    }
}