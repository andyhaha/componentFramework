package com.andy.modules

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.andy.modularization.ActivityContainer
import com.andy.modularization.ActivityLifeCycle
import com.andy.modules.databinding.ActivityMainBinding
import java.lang.ref.WeakReference

class MainActivityDelegate(
    private val activity: MainActivity,
    private val binding: ActivityMainBinding,
) : ActivityLifeCycle {
    init {
        activity.lifecycle.addObserver(InnerLifecycleObserver(this))
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

    class InnerLifecycleObserver(delegate: MainActivityDelegate) : LifecycleEventObserver {
        private val activityDelegate = WeakReference(delegate)

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    activityDelegate.get()?.onCreate()
                }
                Lifecycle.Event.ON_START -> {
                    activityDelegate.get()?.onStart()
                }
                Lifecycle.Event.ON_RESUME -> {
                    activityDelegate.get()?.onResume()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    activityDelegate.get()?.onPause()
                }
                Lifecycle.Event.ON_STOP -> {
                    activityDelegate.get()?.onStop()
                }
                Lifecycle.Event.ON_DESTROY -> {
                    activityDelegate.get()?.onDestroy()
                }
                else -> {
                    // Handle other lifecycle events here if needed
                }
            }
        }
    }
}