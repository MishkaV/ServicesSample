package io.mishkav.sberservices.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import io.mishkav.sberservices.utils.Constants

class BindedService : Service() {
    private var code = 0
    private val binder = LocalBinder()

    override fun onCreate() {
        super.onCreate()
        code = (0..10000).random()
        Log.d(Constants.TAG_SBER_SERVICE, TAG_SERVICE + Constants.ON_CREATE + "-$code")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(Constants.TAG_SBER_SERVICE, Constants.ON_START_COMMAND)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(Constants.TAG_SBER_SERVICE, TAG_SERVICE + Constants.ON_DESTROY + "-$code")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(Constants.TAG_SBER_SERVICE, TAG_SERVICE + Constants.ON_BIND + "-$code")
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService(): BindedService = this@BindedService
    }

    companion object {
        private const val TAG_SERVICE = "BINDED_SERVICE: "
    }
}