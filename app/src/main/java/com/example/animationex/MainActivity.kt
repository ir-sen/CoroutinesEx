package com.example.animationex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.animationex.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.d("MyLog", "Handler Message $msg")
        }
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            loadDate()
        }
        handler.sendMessage(Message.obtain(handler, 0, 17))
    }

    private fun loadDate() {
        // Вначеле видемое
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        loadCity {
            binding.tvLocation.text = it
            loadTemperature(it) {
                binding.tvTemperature.text = it.toString()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true
            }
        }


    }

    private fun loadCity(callback: (String) -> Unit) {
        // Перерыв
        thread {
            Thread.sleep(5000)
            Handler(Looper.getMainLooper()).post {
                callback.invoke("Moscow")
            }
        }
    }

    private fun loadTemperature(city: String, callback: (Int) -> Unit) {
        thread {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(
                    this,
                    "Loading temperature for city: {city}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Thread.sleep(5000)

            Handler(Looper.getMainLooper()).post {
                callback.invoke(17)
            }
        }
    }

}