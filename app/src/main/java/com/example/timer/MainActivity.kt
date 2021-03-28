package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var time_when_stopped: Long = 0
    var isRunning = false

    lateinit var btn_start_pause: Button
    lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_pause = findViewById(R.id.btn_start_pause)
        val btn_reset: Button = findViewById(R.id.btn_reset)
        val btn_activity2: Button = findViewById(R.id.btn_activity2)

        chronometer = findViewById(R.id.chronometer)

        btn_start_pause.setOnClickListener() {
            if (!isRunning) {
                chronometer.base = SystemClock.elapsedRealtime() + time_when_stopped
                chronometer.start()
                btn_start_pause.text = "Pause"
                isRunning = true
            } else {
                time_when_stopped = chronometer.base - SystemClock.elapsedRealtime()
                chronometer.stop()
                btn_start_pause.text = "Start"
                isRunning = false
            }
        }

        btn_reset.setOnClickListener() {
            chronometer.base = SystemClock.elapsedRealtime()
            time_when_stopped = 0
        }

        btn_activity2.setOnClickListener() {
            val intent = Intent(this, activity2::class.java)
            if (isRunning) {
                intent.putExtra("time", chronometer.base)
            }else{
                chronometer.base = SystemClock.elapsedRealtime() + time_when_stopped
                intent.putExtra("time", chronometer.base)
            }
            startActivity(intent)
        }

    }

    override fun onStop() {
        super.onStop()
        chronometer.stop()
        isRunning = false
        btn_start_pause.text = "Start"
        time_when_stopped = chronometer.base - SystemClock.elapsedRealtime()
        Log.d("taggg", "onStop")
    }

}