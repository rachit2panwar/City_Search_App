package com.example.citySearchApplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citySearchApplication.service.repository.CalendarRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CitySearchActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var calendarRepository: CalendarRepository

    private lateinit var viewModel: CitySearchActivityViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CitySearchActivityViewModel(calendarRepository)
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
