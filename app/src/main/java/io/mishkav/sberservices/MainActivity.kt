package io.mishkav.sberservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.mishkav.sberservices.services.DemonService
import io.mishkav.sberservices.services.ForegroundService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDemonService()
        initForegroundService()
        initWorkService()
        initBindedService()
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
    }

    private fun initBindedService() {
    }
}