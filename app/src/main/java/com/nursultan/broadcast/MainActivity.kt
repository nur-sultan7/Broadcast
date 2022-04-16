package com.nursultan.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private val broadcastReceiver = MyBroadcastReceiver()
    private val loadedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                "loaded" -> {
                    val percent = intent.getIntExtra("progress", 0)
                    progressBar.progress = percent
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnClick).setOnClickListener {
            Intent(MyBroadcastReceiver.ACTION_CLICKED).apply {
                putExtra(MyBroadcastReceiver.EXTRA_COUNT, clickedCount++)
                sendBroadcast(this)
            }
        }
        progressBar = findViewById(R.id.progressBar)

        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(MyBroadcastReceiver.ACTION_CLICKED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
        registerReceiver(loadedReceiver, IntentFilter().apply {
            addAction("loaded")
        })
        Intent(this, MyService::class.java).apply {
            startService(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    companion object {
        var clickedCount = 0
    }
}