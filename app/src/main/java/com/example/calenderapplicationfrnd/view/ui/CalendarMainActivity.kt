package com.example.calenderapplicationfrnd.view.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calenderapplicationfrnd.R
import com.example.calenderapplicationfrnd.databinding.ActivityCalenderMainBinding
import com.example.calenderapplicationfrnd.databinding.AddTaskDialogLayoutBinding
import com.example.calenderapplicationfrnd.service.model.response.TaskDetail
import com.example.calenderapplicationfrnd.service.repository.CalendarRepository
import com.example.calenderapplicationfrnd.utils.Result
import com.example.calenderapplicationfrnd.utils.Utils
import com.example.calenderapplicationfrnd.view.adapter.CalendarAdapter
import com.example.calenderapplicationfrnd.view.adapter.CalendarItemViews
import com.example.calenderapplicationfrnd.view.adapter.TaskAdapter
import com.example.calenderapplicationfrnd.view.adapter.TaskItemViews
import com.example.calenderapplicationfrnd.view.callback.CalendarItemClickListener
import com.example.calenderapplicationfrnd.view.callback.TaskItemClickListener
import com.example.calenderapplicationfrnd.viewmodel.CalendarActivityViewModel
import com.example.calenderapplicationfrnd.viewmodel.CalendarViewModelProviderFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CalendarMainActivity : ComponentActivity(), CalendarItemClickListener, TaskItemClickListener {

    private lateinit var viewModel: CalendarActivityViewModel
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var gestureDetector: GestureDetector
    private var taskAdapterList = mutableListOf<TaskItemViews>()

    private val binding: ActivityCalenderMainBinding by lazy {
        ActivityCalenderMainBinding.inflate(LayoutInflater.from(this))
    }

    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    private var taskRecyclerView: RecyclerView? = null
    private var selectedDate: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val repository = CalendarRepository()
        val provider = CalendarViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, provider).get(CalendarActivityViewModel::class.java)
        initWidgets()
        selectedDate = LocalDate.now()
        setMonthView()
        setTaskAdapter()
        observeData()
    }

    private fun observeData() {
        viewModel.observeCalendarTaskList().observe(this) { response ->
            when (response) {
                is Result.Success -> {
                    hideProgressBar()
                    response.data?.let { taskList ->
                        binding.titleTaskList.isVisible = taskList.tasks.size > 0
                        val list = viewModel.mapResponseToTaskItem(taskList)
                        taskAdapter.submitList(list)
                        taskAdapterList = list.toMutableList()
                    }
                }

                is Result.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.d("An error occured: ", message)
                    }
                }

                is Result.Loading -> {
                    showProgressBar()
                }
            }
        }

        viewModel.observeDeleteCalendarTask().observe(this) { response ->
            when (response) {
                is Result.Success -> {
                    response.data?.let { it ->
                        if (it.status == "Success") {
                            viewModel.deleteTaskAndUpdateList(taskAdapter.currentList, it.taskId)
                                ?.let { pos ->
                                    taskAdapter.removeItemFromList(pos)
                                }
                            Toast.makeText(this, "Task Deleted", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Error! Please try again", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                is Result.Error -> {
                    Toast.makeText(this, "Error! Please try again", Toast.LENGTH_SHORT).show()
                    response.message?.let { message ->
                        Log.d("An error occured: ", message)
                    }
                }

                is Result.Loading -> {
                }
            }
        }

        //better approach this that BE returns us the new Task Detail in response, so that we can show user task detail with updated task_id
        viewModel.observeStoreCalendarTask().observe(this) { response ->
            when (response) {
                is Result.Success -> {
                    response.data?.let { it ->
                        if (it.status == "Success") {
                            Toast.makeText(this, "Task Stored Successfully, Please refresh", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "Error! Please try again", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                is Result.Error -> {
                    Toast.makeText(this, "Error! Please try again", Toast.LENGTH_SHORT).show()
                    response.message?.let { message ->
                        Log.d("An error occured: ", message)
                    }
                }

                is Result.Loading -> {}
            }
        }
    }

    private fun hideProgressBar() {
    }

    private fun showProgressBar() {
    }

    private fun initWidgets() {
        calendarRecyclerView = binding.calendarRecyclerView
        taskRecyclerView = binding.tasksRecyclerView
        monthYearText = binding.monthYearTV
        gestureDetector = GestureDetector(this, GestureListener())
    }

    private fun setMonthView() {
        monthYearText?.text = Utils.monthYearFromDate(selectedDate)
        val daysInMonth = selectedDate?.let {
            Utils.daysInMonthArray(it)
        } ?: arrayListOf()
        val calendarAdapter = CalendarAdapter(this)
        calendarAdapter.submitList(daysInMonth)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView?.layoutManager = layoutManager
        calendarRecyclerView?.adapter = calendarAdapter
        calendarRecyclerView?.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    private fun setTaskAdapter() {
        taskAdapter = TaskAdapter(this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        taskRecyclerView?.layoutManager = layoutManager
        taskRecyclerView?.adapter = taskAdapter
    }

    fun previousMonthAction(view: View?) {
        selectedDate = selectedDate?.minusMonths(1)
        setMonthView()
    }

    fun nextMonthAction(view: View?) {
        selectedDate = selectedDate?.plusMonths(1)
        setMonthView()
    }


    private fun showTaskInputDialog(date: LocalDate) {
        val dialog = Dialog(this@CalendarMainActivity, R.style.CustomDialog)
        val dialogBinding = AddTaskDialogLayoutBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(dialogBinding.root)

        with(dialogBinding) {
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val formattedDate: String = date.format(formatter)
            titleDialog.text = "Add Task Here for ${formattedDate}"
            buttonSubmit.setOnClickListener {
                val title = editTextTitle.text.toString()
                val description = editTextDescription.text.toString()

                val taskDetail = TaskDetail(
                    title = title,
                    description = description,
                    date = formattedDate
                )

                viewModel.storeCalendarTask(taskDetail)

                dialog.dismiss()
            }
        }

        dialog.show()
    }

    override fun onCalendarItemClicked(item: CalendarItemViews.CalendarDate) {
        item.localDate?.let {
            if (item.title != "") {
                selectedDate = item.localDate
                selectedDate?.let { showTaskInputDialog(it) }
                    ?: Toast.makeText(this, "Selected Date is null", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Go to month view to Add this task", Toast.LENGTH_LONG).show()
    }

    // I had 2 cases either I could have passed position here, but that can cause issue if 2 elements are deleted simulaneously then position will be messed.
    override fun onTaskItemClicked(item: TaskItemViews.TaskItem) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Deleting Task")
        builder.setMessage("Do you want to delete the selected Task?")

        builder.setPositiveButton("YES") { dialog, which ->
            if (item.taskId != null) {
                viewModel.deleteCalendarTask(item.taskId)
            } else {
                Toast.makeText(this, "TaskID is null", Toast.LENGTH_LONG).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("NO") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 400
        private val SWIPE_VELOCITY_THRESHOLD = 400


        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffX = e2.x - e1?.x!!
                val diffY = e2.y - e1.y

                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                    }
                }
                result = true
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }
    }

    private fun onSwipeRight() {
        selectedDate = selectedDate?.minusMonths(1)
        setMonthView()
    }

    private fun onSwipeLeft() {
        selectedDate = selectedDate?.plusMonths(1)
        setMonthView()
    }
}