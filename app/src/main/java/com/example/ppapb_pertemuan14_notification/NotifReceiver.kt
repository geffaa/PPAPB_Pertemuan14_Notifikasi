package com.example.ppapb_pertemuan14_notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotifReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val msg = intent.getStringExtra("Message")
        if (msg != null) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        // Start MainActivity after showing the Toast
        val activityIntent = Intent(context, MainActivity::class.java)
        activityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(activityIntent)
    }
}

