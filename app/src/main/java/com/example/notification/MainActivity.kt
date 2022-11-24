package com.example.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "1"
    lateinit var push:Button
    var c:Int = 0
    lateinit var mainBinding:ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        push = findViewById(R.id.button)

        push.setOnClickListener {

            c++
            mainBinding.button.text = c.toString()
            if (c % 5 == 0)
            {
                sendNotification()
            }

        }

    }
    fun sendNotification()
    {
        val builder =  NotificationCompat.Builder(applicationContext,CHANNEL_ID)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(CHANNEL_ID,"1",NotificationManager.IMPORTANCE_DEFAULT)
            val manager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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
        val noficationManagerCompat = NotificationManagerCompat.from(applicationContext)
        noficationManagerCompat.notify(1,builder.build())



    }
}