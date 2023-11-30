package com.example.ppapb_pertemuan14_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.ppapb_pertemuan14_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId = "TEST_NOTIF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        binding.btnUpdate.setOnClickListener {
            val notifImage = BitmapFactory.decodeResource(resources, R.drawable.yodhim)
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Notifikasi Saya")
                .setContentText("Hello World")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setLargeIcon(notifImage)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(notifImage)
                        .bigLargeIcon(null)
                )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Notifku",
                    NotificationManager.IMPORTANCE_HIGH
                )
                with(notificationManager) {
                    createNotificationChannel(channel)
                    notify(0, builder.build())
                }
            } else {
                notificationManager.notify(0, builder.build())
            }
        }

        binding.btnNotif.setOnClickListener{
            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_MUTABLE
            } else {
                0
            }

            val intent = Intent(this, NotifReceiver::class.java).putExtra("Message", "Baca Selengkapnya")
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, flag)

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Notifikasi Saya")
                .setContentText("Hello World")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .addAction(0, "Baca Selengkapnya", pendingIntent)



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Notifku",
                    NotificationManager.IMPORTANCE_HIGH
                )
                with(notificationManager) {
                    createNotificationChannel(channel)
                    notify(0, builder.build())
                }
            } else {
                notificationManager.notify(0, builder.build())
            }
        }
    }
}