package io.mishkav.sberservices.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.mishkav.sberservices.MainActivity
import io.mishkav.sberservices.R
import io.mishkav.sberservices.utils.Constants

class ForegroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG_SERVICE, Constants.ON_CREATE)
        createChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG_SERVICE, Constants.ON_START_COMMAND)
        showNotification()
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG_SERVICE, Constants.ON_DESTROY)
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG_SERVICE, Constants.ON_BIND)
        return null
    }

    private fun createChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)

        manager.createNotificationChannel(serviceChannel)
    }

    private fun showNotification() {
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }
        val notification: Notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(NOTIFICATION_CONTENT)
            .setSmallIcon(R.drawable.ic_people)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    companion object {
        private const val TAG_SERVICE = "FOREGROUND_SERVICE"
        private const val CHANNEL_ID = "CHANNEL ID"
        private const val CHANNEL_NAME = "CHANNEL NAME"
        private const val NOTIFICATION_ID = 123
        private const val NOTIFICATION_TITLE = "NOTIFICATION TITLE"
        private const val NOTIFICATION_CONTENT = "NOTIFICATION CONTENT"
    }
}