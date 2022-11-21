package com.nursultan.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.nursultan.broadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val localBroadcastReceiver by lazy {
        LocalBroadcastManager.getInstance(this)
    }
    private val broadcastReceiver = MyBroadcastReceiver()
    private val progressReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            when (p1?.action) {
                "loading" -> {
                    binding.progressBar.progress = p1.getIntExtra("progress", 0)
                }
            }
        }
    }
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnClick.setOnClickListener {
            Intent(MyBroadcastReceiver.ACTION_CLICKED).apply {
                putExtra(MyBroadcastReceiver.EXTRA_COUNT, ++clickedCount)
                localBroadcastReceiver.sendBroadcast(this)
            }
        }


        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(MyBroadcastReceiver.ACTION_CLICKED)
        }
        localBroadcastReceiver.registerReceiver(broadcastReceiver, intentFilter)
        IntentFilter().apply {
            addAction("loading")
            registerReceiver(progressReceiver, this)
        }

        Intent(this, MyService::class.java).apply {
            startService(this)
        }
        Intent(this, MyService2::class.java).apply {
            startService(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastReceiver.unregisterReceiver(broadcastReceiver)
        unregisterReceiver(progressReceiver)
    }

    companion object {
        var clickedCount = 0
    }
}