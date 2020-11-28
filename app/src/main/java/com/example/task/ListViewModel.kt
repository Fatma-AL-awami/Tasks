package com.example.task

import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {

    private val taskRepository = TaskRepository.get()
    val taskListLiveData = taskRepository.getTask()
    val task2ListLiveData = taskRepository.getTask()
    val task3ListLiveData = taskRepository.getTask()

    fun addCrime(task: Task) {
        taskRepository.addTask(task)
    }

}
