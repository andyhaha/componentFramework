package com.andy.modularization

/**
 * Interface representing the basic lifecycle methods of an Activity.
 * These methods mirror the lifecycle stages of an activity, from creation to destruction.
 */
interface ActivityLifeCycle {

    /**
     * Called when the activity is first created.
     * This is where you should initialize your activity, set up resources, and create any necessary UI components.
     */
    fun onCreate()

    /**
     * Called when the activity is about to become visible.
     * This is the appropriate place to initialize things that require the activity to be visible,
     * such as starting animations or resuming services.
     */
    fun onStart()

    /**
     * Called when the activity has become visible and is now in the foreground (it is "resumed").
     * This is the place to interact with the user, start animations, or acquire resources that
     * should be available during the active interaction with the activity.
     */
    fun onResume()

    /**
     * Called when the activity is no longer visible because another activity is in the foreground.
     * This is where you should pause ongoing operations, save data, or stop any UI updates that
     * don't need to occur while the activity is paused.
     */
    fun onPause()

    /**
     * Called when the activity is no longer visible and will soon be destroyed.
     * This method allows for releasing resources or stopping processes that should not continue
     * once the activity is no longer visible.
     */
    fun onStop()

    /**
     * Called before the activity is destroyed.
     * Use this method to clean up resources, save any necessary data, and finalize operations
     * that require activity destruction.
     */
    fun onDestroy()
}