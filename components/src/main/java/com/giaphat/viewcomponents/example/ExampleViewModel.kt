package com.giaphat.viewcomponents.example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giaphat.viewcomponents.views.DisplayName

class ExampleViewModel : ViewModel() {

    val text = MutableLiveData("Default")
    var selectedSpinnerIdx = MutableLiveData<Int>(null)
    var listJob = MutableLiveData<List<Job>>(listOf())

    fun loadListJob() {
        listJob.value = listOf(Job(1, "Job 1"), Job(2, "Job 2"), Job(3, "Job 3"), Job(4, "Job 4"))
    }

}

data class Job(val id: Int, val name: String, override val displayName: String = name) : DisplayName