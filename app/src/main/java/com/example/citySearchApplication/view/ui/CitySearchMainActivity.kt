package com.example.citySearchApplication.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citySearchApplication.databinding.ActivityCitySearchMainBinding
import com.example.citySearchApplication.service.repository.CitySearchRepository
import com.example.citySearchApplication.utils.Result
import com.example.citySearchApplication.view.adapter.CityListAdapter
import com.example.citySearchApplication.viewmodel.CitySearchActivityViewModel
import com.example.citySearchApplication.viewmodel.CitySearchViewModelProviderFactory


class CitySearchMainActivity : ComponentActivity() {

    private lateinit var viewModel: CitySearchActivityViewModel
    private lateinit var taskAdapter: CityListAdapter

    private val binding: ActivityCitySearchMainBinding by lazy {
        ActivityCitySearchMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val repository = CitySearchRepository()
        val provider = CitySearchViewModelProviderFactory(repository)
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
                        Toast.makeText(this@CitySearchMainActivity, "API error occured + ${message}", Toast.LENGTH_LONG).show()
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
        binding.loading.isVisible = false
    }

    private fun showProgressBar() {
        binding.loading.isVisible = true
    }

    private fun setTaskAdapter() {
        taskAdapter = CityListAdapter()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.cityListRecyclerView.layoutManager = layoutManager
        binding.cityListRecyclerView.adapter = taskAdapter
    }
}