package com.example.week2native.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.week2native.domain.Task
import com.example.week2native.domain.mockTasks

class TaskViewModel : ViewModel() {

    // Koko lista pysyy tällä tallessa
    private var allTasks = mutableListOf<Task>().apply { addAll(mockTasks) }

    // Tää näkyy UI:ssa
    var tasks by mutableStateOf(allTasks.toList())
        private set

    fun addTask(task: Task) {
        allTasks.add(task)
        tasks = allTasks.toList()
    }

    fun removeTask(id: Int) {
        tasks = tasks.filterNot { it.id == id }
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { task ->
            if (task.id == id) task.copy(done = !task.done) else task
        }.toMutableList()
        tasks = allTasks.toList()
    }

    // Filteri vain näyttää tai piilottaa, ei poista
    fun filterByDone(done: Boolean) {
        tasks = allTasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }

    fun showAll() {
        tasks = allTasks.toList()
    }
}
