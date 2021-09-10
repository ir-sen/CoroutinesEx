package com.example.animationex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.animationex.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // create life cycle
        binding.buttonLoad.setOnClickListener {
            lifecycleScope.launch {
                loadDate()
            }
        }

    }

    private suspend fun loadDate() {
        Log.d("MainActivity", "Load started: $this")
        // Вначеле видемое
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        val city = loadCity()

        binding.tvLocation.text = city
        val temper = loadTemperature(city)

        binding.tvTemperature.text = temper.toString()
        binding.progress.isVisible = false
        binding.buttonLoad.isEnabled = true
    }



    private suspend fun loadCity() : String {
        delay(5000)
        return ("Moscow")
    }

    private suspend fun loadTemperature(city: String): Int {
        Toast.makeText(this,
        "This is $city",
            Toast.LENGTH_SHORT
        ).show()
        delay(5000)
        return 7
    }


}
