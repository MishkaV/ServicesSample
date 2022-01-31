package io.mishkav.sberservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import io.mishkav.sberservices.services.BindedService
import io.mishkav.sberservices.services.DemonService
import io.mishkav.sberservices.services.ForegroundService
import io.mishkav.sberservices.services.WorkService

class MainActivity : AppCompatActivity() {
    private lateinit var bindedService: BindedService
    private var bound: Boolean = false
    private lateinit var demonIntent: Intent
    private lateinit var foregroundIntent: Intent
    private lateinit var workIntent: Intent
    private lateinit var bindedIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initIntens()
        initButtons()
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        bound = false
    }

    private fun initIntens() {
        demonIntent = Intent(this, DemonService::class.java)
        foregroundIntent = Intent(this, ForegroundService::class.java)
        workIntent = Intent(this, WorkService::class.java)
        bindedIntent = Intent(this, BindedService::class.java)
    }

    private fun initButtons() {
        //Demon
        findViewById<View>(R.id.start_demon).setOnClickListener {
            startService(demonIntent)
        }
        findViewById<View>(R.id.stop_demon).setOnClickListener {
            stopService(demonIntent)
        }

        //Foreground
        findViewById<View>(R.id.start_foreground).setOnClickListener {
            startService(foregroundIntent)
        }
        findViewById<View>(R.id.stop_foreground).setOnClickListener {
            stopService(foregroundIntent)
        }

        //Work
        findViewById<View>(R.id.start_work).setOnClickListener {
            startService(workIntent)
        }
        findViewById<View>(R.id.stop_work).setOnClickListener {
            stopService(workIntent)
        }

        //Binded
        findViewById<View>(R.id.start_binded).setOnClickListener {
            bindService(bindedIntent, connection, Context.BIND_AUTO_CREATE)
        }
        findViewById<View>(R.id.stop_binded).setOnClickListener {
            unbindService(connection)
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