package com.andy.modularization

import android.content.Intent
import android.os.Bundle

interface StateHandle {
    /**
     * Called when the activity is recovering from a previously saved state (if applicable).
     * This method is invoked after the activity is recreated and can be used to restore
     * any state that was saved during the previous instance.
     */
    fun onRestoreInstanceState(savedInstanceState: Bundle?)

    /**
     * Called when the activity saves its state before it is stopped or destroyed.
     * This method allows the activity to save important data in the `outState` bundle
     * that can be restored later if the activity is recreated.
     */
    fun onSaveInstanceState(outState: Bundle)

    /**
     * Optional: Handle new Intent if the activity was already created.
     * This method is triggered when a new intent is passed to an activity that is already running.
     * Useful for handling changes in data or UI updates based on the new intent.
     */
    fun onNewIntent(intent: Intent)
}