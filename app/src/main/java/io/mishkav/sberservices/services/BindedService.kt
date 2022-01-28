package io.mishkav.sberservices.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import io.mishkav.sberservices.utils.Constants

class BindedService : Service() {

    private val binder = LocalBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG_SERVICE, Constants.ON_CREATE)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG_SERVICE, Constants.ON_START_COMMAND)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG_SERVICE, Constants.ON_DESTROY)
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG_SERVICE, Constants.ON_BIND)
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService(): BindedService = this@BindedService
    }

    companion object {
        private const val TAG_SERVICE = "BINDED_SERVICE"
    }
}