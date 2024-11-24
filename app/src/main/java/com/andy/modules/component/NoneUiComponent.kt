package com.andy.modules.component

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.andy.modularization.Component

/**
 * A component that is unrelated to the UI.
 * It handles non-visual logic or background tasks.
 */
class NoneUiComponent(
    override val activity: AppCompatActivity
) : Component() {

    companion object {
        private const val TAG = "NoneUiComponent"
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: Initializing NoneUiComponent")
    }

    override fun onStart() {
        Log.d(TAG, "onStart: NoneUiComponent started")
    }

    override fun onResume() {
        Log.d(TAG, "onResume: NoneUiComponent resumed")
    }

    override fun onPause() {
        Log.d(TAG, "onPause: NoneUiComponent paused")
    }

    override fun onStop() {
        Log.d(TAG, "onStop: NoneUiComponent stopped")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: NoneUiComponent destroyed")
    }

}