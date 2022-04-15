package com.nursultan.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_AIRPLANE_MODE_CHANGED ->{
                Toast.makeText(context, "Airplane mode is changed", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_LOW ->{
                Toast.makeText(context, "Battery is low", Toast.LENGTH_SHORT).show()
            }
        }
    }
}