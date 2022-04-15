package com.nursultan.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    private var batteryLevel = -1
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
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
                Toast.makeText(context, "Battery is low: $batteryLevel", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_CHANGED -> {
                batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            }
        }
    }
}