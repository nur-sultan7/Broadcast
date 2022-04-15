package com.nursultan.broadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val broadcastReceiver = MyBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnClick).setOnClickListener {
            Intent(MyBroadcastReceiver.ACTION_CLICKED).apply {
                putExtra(MyBroadcastReceiver.EXTRA_COUNT, clickedCount++)
                sendBroadcast(this)
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(MyBroadcastReceiver.ACTION_CLICKED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    companion object {
        var clickedCount = 0
    }
}