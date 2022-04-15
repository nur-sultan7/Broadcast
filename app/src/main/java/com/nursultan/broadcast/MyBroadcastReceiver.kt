package com.nursultan.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ACTION_CLICKED -> {
                Toast.makeText(
                    context,
                    "Click count: ${intent.getIntExtra(EXTRA_COUNT, -1)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val modeState = intent.getBooleanExtra("state", false)
                Toast.makeText(
                    context,
                    "Airplane mode is changed: $modeState",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "Battery is low", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_CHANGED -> {
                Toast.makeText(
                    context,
                    "Battery is: ${intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        const val ACTION_CLICKED = "clicked"
        const val EXTRA_COUNT = "count"
    }
}