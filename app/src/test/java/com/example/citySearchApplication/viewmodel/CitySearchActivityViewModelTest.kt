package com.example.citySearchApplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citySearchApplication.service.repository.CitySearchRepository
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
    private lateinit var citySearchRepository: CitySearchRepository

    private lateinit var viewModel: CitySearchActivityViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CitySearchActivityViewModel(citySearchRepository)
    }
}
