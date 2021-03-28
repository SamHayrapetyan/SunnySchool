package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        var isRunning = false

        val btn_start_pause2: Button = findViewById(R.id.btn_start_pause2)
        val btn_reset2: Button = findViewById(R.id.btn_reset2)
        val btn_activity_main: Button = findViewById(R.id.btn_activity_main)

        val chronometer2: Chronometer = findViewById(R.id.chronometer2)

        chronometer2.base = intent.getLongExtra("time", 0)
        var time_when_stopped = chronometer2.base - SystemClock.elapsedRealtime()

        btn_start_pause2.setOnClickListener() {
            if (!isRunning) {
                chronometer2.base = SystemClock.elapsedRealtime() + time_when_stopped
                chronometer2.start()
                btn_start_pause2.text = "Pause"
                isRunning = true
            } else {
                time_when_stopped = chronometer2.base - SystemClock.elapsedRealtime()
                chronometer2.stop()
                btn_start_pause2.text = "Start"
                isRunning = false
            }
        }

        btn_reset2.setOnClickListener() {
            chronometer2.base = SystemClock.elapsedRealtime()
            time_when_stopped = 0
        }

        btn_activity_main.setOnClickListener(){
            chronometer2.stop()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}