package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

class NotificationReceiver : BroadcastReceiver() {

    private val CHANNEL_ID = "1"

    override fun onReceive(context: Context?, intent: Intent?) {

        if (context != null)
        {
            val builder =  NotificationCompat.Builder(context,CHANNEL_ID)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                val channel = NotificationChannel(CHANNEL_ID,"1", NotificationManager.IMPORTANCE_HIGH)
                val manager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)
                builder.setSmallIcon(R.drawable.notification)
                    .setContentTitle("Title")
                    .setContentText("Notification Text")
            }
            else
            {
                builder.setSmallIcon(R.drawable.notification)
                    .setContentTitle("Notification Title")
                    .setContentText("this is a notification text")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }
            val noficationManagerCompat = NotificationManagerCompat.from(context)
            noficationManagerCompat.notify(1,builder.build())


        }
    }
}