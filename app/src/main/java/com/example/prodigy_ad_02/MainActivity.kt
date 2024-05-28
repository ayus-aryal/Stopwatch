package com.example.prodigy_ad_02

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prodigy_ad_02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    private var isRunning = false
    private var timerSeconds = 0

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable{
        override fun run() {
            timerSeconds++
            val hours = timerSeconds/3600
            val minutes = (timerSeconds%3600)/60
            val seconds = timerSeconds%60

            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.timerText.text = time

            handler.postDelayed(this, 1000)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener{
            startTimer()
        }
        binding.stopButton.setOnClickListener{
            stopTimer()
        }
        binding.resetButton.setOnClickListener{
            resetTimer()
        }


    }


    private fun startTimer(){
        if(!isRunning){
            handler.postDelayed(runnable, 1000)
            isRunning = true

            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }
    }

    private fun stopTimer(){
        if(isRunning){
            handler.removeCallbacks(runnable)
            isRunning = false

            binding.startButton.isEnabled = true
            binding.startButton.text = "Resume"
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true

        }
    }

    private fun resetTimer(){
        stopTimer()

        timerSeconds = 0
        binding.timerText.text = "00:00:00"

        binding.startButton.isEnabled = true
        binding.resetButton.isEnabled = false
        binding.startButton.text = "Start"
    }



}
