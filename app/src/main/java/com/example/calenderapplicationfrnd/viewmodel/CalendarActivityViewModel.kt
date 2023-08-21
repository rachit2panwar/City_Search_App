package com.example.calenderapplicationfrnd.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calenderapplicationfrnd.service.model.request.DeleteCalendarTaskRequest
import com.example.calenderapplicationfrnd.service.model.request.GetCalendarListRequest
import com.example.calenderapplicationfrnd.service.model.request.StoreCalendarTaskRequest
import com.example.calenderapplicationfrnd.service.model.response.CalendarStatusResponse
import com.example.calenderapplicationfrnd.service.model.response.GetCalendarResponse
import com.example.calenderapplicationfrnd.service.model.response.TaskDetail
import com.example.calenderapplicationfrnd.service.repository.CalendarRepository
import com.example.calenderapplicationfrnd.utils.Result
import com.example.calenderapplicationfrnd.view.adapter.TaskItemViews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

const val USER_ID = 1
class CalendarActivityViewModel(private val calendarRepository: CalendarRepository) : ViewModel() {

    private var calendarTaskList = MutableLiveData<Result<GetCalendarResponse>>()
    fun observeCalendarTaskList(): LiveData<Result<GetCalendarResponse>> {
        return calendarTaskList
    }

    private var storeCalendarTask = MutableLiveData<Result<CalendarStatusResponse>>()
    fun observeStoreCalendarTask(): LiveData<Result<CalendarStatusResponse>> {
        return storeCalendarTask
    }

    private var deleteCalendarTask = MutableLiveData<Result<CalendarStatusResponse>>()
    fun observeDeleteCalendarTask(): LiveData<Result<CalendarStatusResponse>> {
        return deleteCalendarTask
    }

    init {
        getCalendarTaskList()
    }

    //for getting Calendar Tasks
    private fun getCalendarTaskList() = viewModelScope.launch(Dispatchers.IO) {
        calendarTaskList.postValue(Result.Loading())
        val response = calendarRepository.getCalendarTask(
            GetCalendarListRequest(
                userId = 1
            )
        )
        calendarTaskList.postValue(handleCalendarTaskListResp(response))
    }

    private fun handleCalendarTaskListResp(response: Response<GetCalendarResponse>): Result<GetCalendarResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Result.Success(resultResponse)
            }
        }
        return Result.Error(response.message())
    }

    //for Storing new Calendar Task
    fun storeCalendarTask(taskDetail: TaskDetail) = viewModelScope.launch(Dispatchers.IO) {
        val response = calendarRepository.storeCalendarTask(
            StoreCalendarTaskRequest(
                userId = USER_ID,
                task = taskDetail
            )
        )
        storeCalendarTask.postValue(handleStoreCalendarTaskResp(response))
    }

    private fun handleStoreCalendarTaskResp(response: Response<CalendarStatusResponse>): Result<CalendarStatusResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Result.Success(resultResponse)
            }
        }
        return Result.Error(response.message())
    }

    //for deleting Calendar Task
    fun deleteCalendarTask(taskId: Int) = viewModelScope.launch(Dispatchers.IO) {
        deleteCalendarTask.postValue(Result.Loading())
        val response = calendarRepository.deleteCalendarTask(
            DeleteCalendarTaskRequest(
                userId = USER_ID,
                taskId = taskId
            )
        )
        deleteCalendarTask.postValue(handleDeleteCalendarTaskResp(response, taskId))
    }

    private fun handleDeleteCalendarTaskResp(response: Response<CalendarStatusResponse>, taskId: Int?): Result<CalendarStatusResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                resultResponse.taskId = taskId
                return Result.Success(resultResponse)
            }
        }
        return Result.Error(response.message())
    }

    fun mapResponseToTaskItem(taskList: GetCalendarResponse): List<TaskItemViews.TaskItem> {
        val listOfTasks = arrayListOf<TaskItemViews.TaskItem>()
        taskList.tasks.forEachIndexed { index, tasks ->
            listOfTasks.add(
                TaskItemViews.TaskItem(
                    title = tasks.taskDetail?.title,
                    description = tasks.taskDetail?.description,
                    date = tasks.taskDetail?.date,
                    taskId = tasks.taskId
                )
            )
        }
        return listOfTasks
    }

    fun deleteTaskAndUpdateList(currentList: MutableList<TaskItemViews>, taskId: Int?): Int? {
        currentList.forEachIndexed { index, taskItemViews ->
            if((taskItemViews as? TaskItemViews.TaskItem)?.taskId == taskId){
                return index
            }
        }
        return null
    }

}