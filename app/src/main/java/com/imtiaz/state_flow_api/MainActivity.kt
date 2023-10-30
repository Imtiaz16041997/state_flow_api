package com.imtiaz.state_flow_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.imtiaz.state_flow_api.databinding.ActivityMainBinding
import com.imtiaz.state_flow_api.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        counterResult()
    }

    private fun counterResult() {

        /*
        //launchWhenStarted this one is deprecated
        lifecycle.launchWhenStarted {
            mainViewModel._counter.collect{counter->
                binding.resultTxtView.text = counter.toString()

            }
        }*/
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel._counter.collect { counter ->
                    binding.resultTxtView.text = counter.toString()
                }
            }
        }
    }

    private fun initUi() {
        binding.incrementBtn.setOnClickListener{
            mainViewModel.increment()
        }

        binding.decrementBtn.setOnClickListener{
            mainViewModel.decrement()
        }
    }
}