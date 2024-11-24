package com.andy.modules.ui

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.andy.modularization.Component
import com.andy.modularization.getString
import com.andy.modules.R
import com.andy.modules.databinding.LayoutBottomBarBinding

class BottomBar(
    override val activity: AppCompatActivity,
    private val binding: LayoutBottomBarBinding,
) : Component() {

    companion object {
        private const val TAG = "BottomBar"
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: Initializing BottomBar")
        binding.textBottom.text = getString(R.string.bottom)
    }

    override fun onStart() {
        Log.d(TAG, "onStart: BottomBar started")
    }

    override fun onResume() {
        Log.d(TAG, "onResume: BottomBar resumed")
    }

    override fun onPause() {
        Log.d(TAG, "onPause: BottomBar paused")
    }

    override fun onStop() {
        Log.d(TAG, "onStop: BottomBar stopped")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: BottomBar destroyed")
    }
}