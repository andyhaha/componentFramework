package com.andy.modules

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.andy.modularization.ActivityContainer
import com.andy.modularization.ActivityLifeCycle
import com.andy.modules.databinding.ActivityMainBinding

class MainActivityDelegate(
    private val activity: MainActivity,
    private val binding: ActivityMainBinding,
) : ActivityLifeCycle {
    init {
        activity.lifecycle.addObserver(InnerLifecycleObserver())
    }

    private val roomContainer by lazy(LazyThreadSafetyMode.NONE) {
        ActivityContainer(activity)
    }

    private val componentFactory by lazy(LazyThreadSafetyMode.NONE) {
        MainComponentFactory(activity, binding)
    }

    override fun onCreate() {
        roomContainer.onCreate()
        componentFactory.newComponents()
        componentFactory.components().forEach {
            roomContainer.addComponent(it)
        }
    }

    override fun onStart() {
        roomContainer.onStart()
    }

    override fun onResume() {
        roomContainer.onResume()
    }

    override fun onPause() {
        roomContainer.onPause()
    }

    override fun onStop() {
        roomContainer.onStop()
    }

    override fun onDestroy() {
        roomContainer.onDestroy()
        componentFactory.release()
    }

    inner class InnerLifecycleObserver : LifecycleEventObserver {

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    onCreate()
                }
                Lifecycle.Event.ON_START -> {
                    onStart()
                }
                Lifecycle.Event.ON_RESUME -> {
                    onResume()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    onPause()
                }
                Lifecycle.Event.ON_STOP -> {
                    onStop()
                }
                Lifecycle.Event.ON_DESTROY -> {
                    onDestroy()
                }
                else -> {
                    // Handle other lifecycle events here if needed
                }
            }
        }
    }
}