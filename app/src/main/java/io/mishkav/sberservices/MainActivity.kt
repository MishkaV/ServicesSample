package io.mishkav.sberservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import io.mishkav.sberservices.services.BindedService
import io.mishkav.sberservices.services.DemonService
import io.mishkav.sberservices.services.ForegroundService
import io.mishkav.sberservices.services.WorkService

class MainActivity : AppCompatActivity() {
    private lateinit var bindedService: BindedService
    private var bound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDemonService()
        initForegroundService()
        initWorkService()
        initBindedService()
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        bound = false
    }

    private fun initDemonService() {
        Intent(this, DemonService::class.java).also { intent ->
            startService(intent)
        }
    }

    private fun initForegroundService() {
        Intent(this, ForegroundService::class.java).also { intent ->
            startService(intent)
        }
    }

    private fun initWorkService() {
        Intent(this, WorkService::class.java).also { intent ->
            startService(intent)
        }
    }

    private fun initBindedService() {
        Intent(this, BindedService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as BindedService.LocalBinder
            bindedService = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }
}