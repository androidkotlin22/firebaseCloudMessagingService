package com.live.firebasecloudmessagingdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.live.firebasecloudmessagingdemo.databinding.ActivityFirebaseCloudMessagingSampleBinding

class FirebaseCloudMessagingSample : AppCompatActivity() {
    private val binding by lazy { ActivityFirebaseCloudMessagingSampleBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /** implementing the fcm cloud messaging service */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "anything you can pass here...",
                "myNotifications", NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
                Toast.makeText(this@FirebaseCloudMessagingSample, msg, Toast.LENGTH_SHORT).show()
            }

    }
}