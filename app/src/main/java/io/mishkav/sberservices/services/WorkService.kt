package io.mishkav.sberservices.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.mishkav.sberservices.utils.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WorkService : Service() {
    private lateinit var someCoroutine: Job

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG_SERVICE, Constants.ON_CREATE)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG_SERVICE, Constants.ON_START_COMMAND)
        someCoroutine = GlobalScope.launch {
            while (true) {

            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG_SERVICE, Constants.ON_DESTROY)
        someCoroutine.cancel()
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG_SERVICE, Constants.ON_BIND)
        return null
    }

    companion object {
        private const val TAG_SERVICE = "WORK_SERVICE"
    }
}