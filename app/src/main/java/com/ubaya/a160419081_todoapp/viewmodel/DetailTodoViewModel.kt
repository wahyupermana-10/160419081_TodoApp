package com.ubaya.a160419081_todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.ubaya.a160419081_todoapp.model.Todo
import com.ubaya.a160419081_todoapp.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    fun addTodo(todo:Todo){
        launch {
            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java, "tododb").build()
            db.todoDao().insertAll(todo)
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}