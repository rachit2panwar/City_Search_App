package com.example.citySearchApplication.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citySearchApplication.databinding.ActivityCalenderMainBinding
import com.example.citySearchApplication.service.repository.CalendarRepository
import com.example.citySearchApplication.utils.Result
import com.example.citySearchApplication.view.adapter.TaskAdapter
import com.example.citySearchApplication.viewmodel.CitySearchActivityViewModel
import com.example.citySearchApplication.viewmodel.CalendarViewModelProviderFactory


class CitySearchMainActivity : ComponentActivity() {

    private lateinit var viewModel: CitySearchActivityViewModel
    private lateinit var taskAdapter: TaskAdapter

    private val binding: ActivityCalenderMainBinding by lazy {
        ActivityCalenderMainBinding.inflate(LayoutInflater.from(this))
    }

    private var taskRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val repository = CalendarRepository()
        val provider = CalendarViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, provider).get(CitySearchActivityViewModel::class.java)
        setTaskAdapter()
        observeData()
        handleUI()
    }

    private fun handleUI() {
        with(binding){
            searchButton.setOnClickListener {
                val text = searchBarEdittext.text.toString()
                if(!text.isEmpty()){
                    viewModel.getCitySearchList(text)
                }else{
                    Toast.makeText(this@CitySearchMainActivity, "search bar is empty, please write to proceed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeData() {
        viewModel.observeCitySearchList().observe(this) { response ->
            when (response) {
                is Result.Success -> {
                    hideProgressBar()
                    response.data?.let { cityList ->
                        val list = viewModel.mapResponseToTaskItem(cityList)
                        taskAdapter.submitList(list)
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

                else -> {}
            }
        }
    }

    private fun hideProgressBar() {
    }

    private fun showProgressBar() {
    }

    private fun setTaskAdapter() {
        taskAdapter = TaskAdapter()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        taskRecyclerView?.layoutManager = layoutManager
        taskRecyclerView?.adapter = taskAdapter
    }
}