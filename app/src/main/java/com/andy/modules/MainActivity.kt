package com.andy.modules

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.andy.modules.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycle.addObserver(InnerLifecycleObserver())
    }

    inner class InnerLifecycleObserver : LifecycleEventObserver {

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    Log.d("LifecycleEventObserver", "Activity created")
                }
                Lifecycle.Event.ON_START -> {
                    Log.d("LifecycleEventObserver", "Activity started")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("LifecycleEventObserver", "Activity resumed")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("LifecycleEventObserver", "Activity paused")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("LifecycleEventObserver", "Activity stopped")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    Log.d("LifecycleEventObserver", "Activity destroyed")
                }
                else -> {
                    // Handle other lifecycle events here if needed
                }
            }
        }
    }
}