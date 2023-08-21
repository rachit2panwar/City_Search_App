package com.example.calenderapplicationfrnd.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.calenderapplicationfrnd.service.model.response.CalendarStatusResponse
import com.example.calenderapplicationfrnd.service.model.response.GetCalendarResponse
import com.example.calenderapplicationfrnd.service.model.response.TaskDetail
import com.example.calenderapplicationfrnd.service.model.response.Tasks
import com.example.calenderapplicationfrnd.service.repository.CalendarRepository
import com.example.calenderapplicationfrnd.view.adapter.TaskItemViews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class CalendarActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var calendarRepository: CalendarRepository

    private lateinit var viewModel: CalendarActivityViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CalendarActivityViewModel(calendarRepository)
    }


//    @Test
//    fun testMapResponseToTaskItem() {
//        val list = arrayListOf<Tasks>()
//        val item = mock(Tasks::class.java)
////            Tasks(taskDetail = TaskDetail(title = "rach",description = "abc",date = "22-7-2022"),taskId = 4)
//        list.add(item)
//        val taskList = GetCalendarResponse(list)
//        val result = viewModel.mapResponseToTaskItem(taskList)
//
//        assertEquals(list.first().taskId ,(result[0].taskId))
//    }

//    @Test
//    fun testDeleteTaskAndUpdateList_TaskIdExists() {
//        val taskId = 1
//        val currentList = mutableListOf<TaskItemViews.TaskItem>()
//        val result = viewModel.deleteTaskAndUpdateList(currentList, taskId)
//        // Add assertions to verify the correctness of the function
//    }
//
//    @Test
//    fun testDeleteTaskAndUpdateList_TaskIdNotFound() {
//        val taskId = 100
//        val currentList = mutableListOf<TaskItemViews.TaskItem>()
//        val result = viewModel.deleteTaskAndUpdateList(currentList, taskId)
//        // Add assertions to verify the correctness of the function
//    }
}
