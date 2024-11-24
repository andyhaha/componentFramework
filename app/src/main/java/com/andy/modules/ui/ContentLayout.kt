package com.andy.modules.ui

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.andy.modularization.Component
import com.andy.modularization.getString
import com.andy.modules.R
import com.andy.modules.databinding.LayoutContentMainBinding

class ContentLayout(
    override val activity: AppCompatActivity,
    private val binding: LayoutContentMainBinding,
) : Component() {

    companion object {
        private const val TAG = "Content"
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: Initializing Content component")
        binding.textContent.text = getString(R.string.content)
    }

    override fun onStart() {
        Log.d(TAG, "onStart: Content component started")
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Content component resumed")
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Content component paused")
    }

    override fun onStop() {
        Log.d(TAG, "onStop: Content component stopped")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Content component destroyed")
    }
}