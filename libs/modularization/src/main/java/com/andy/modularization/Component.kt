package com.andy.modularization

import android.content.Context
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope

/**
 * Extension function for obtaining a ViewModel from a Component.
 * It uses the ViewModelProvider associated with the Activity and the provided factory.
 */
@MainThread
inline fun <reified VM : ViewModel> Component.viewModels(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        (activity as? AppCompatActivity)?.defaultViewModelProviderFactory
            ?: throw IllegalStateException("Activity must be AppCompatActivity")
    }

    return ViewModelLazy(VM::class, { activity.viewModelStore }, factoryPromise)
}

/**
 * Extension property to retrieve the lifecycleScope for the component's Activity.
 */
val Component.lifecycleScope: LifecycleCoroutineScope
    get() = (activity as? AppCompatActivity)?.lifecycle?.coroutineScope
        ?: throw IllegalStateException("Activity must be AppCompatActivity")

/**
 * Extension property to retrieve the applicationContext for the component's Activity.
 */
val Component.applicationContext: Context
    get() = activity.applicationContext

fun Component.getString(@StringRes resId: Int): String {
    return activity.getString(resId)
}

/**
 * Abstract class that represents a component which mimics an Activity's lifecycle methods.
 * This class is responsible for managing a component's lifecycle and can be inherited
 * by other components to implement custom lifecycle behavior.
 */
abstract class Component : ActivityLifeCycle {
    // The Activity associated with this component.
    abstract val activity: AppCompatActivity

    /**
     * This method is responsible for performing a reload of the component.
     * It can be overridden by subclasses to implement specific reload behavior.
     */
    fun performReload() {
        reload()
    }

    /**
     * This method simulates the onCreate() lifecycle callback for the component.
     * It should be called when the component is created.
     */
    fun performCreate() {
        onCreate()
    }

    /**
     * This method simulates the onResume() lifecycle callback for the component.
     * It should be called when the component is resumed.
     */
    fun performResume() {
        onResume()
    }

    /**
     * This method simulates the onPause() lifecycle callback for the component.
     * It should be called when the component is paused.
     */
    fun performPause() {
        onPause()
    }

    /**
     * This method simulates the onStop() lifecycle callback for the component.
     * It should be called when the component is stopped.
     */
    fun performStop() {
        onStop()
    }

    /**
     * This method is called when a component is destroyed.
     * It triggers the onDestroy() lifecycle callback and cleans up the component.
     */
    fun performDestroy() {
        onDestroy()
    }

    /**
     * This is a hook for subclasses to implement specific reload logic when needed.
     * It can be overridden by subclasses to define custom reload behavior.
     */
    protected open fun reload() {
        // Default reload logic can be implemented here if necessary.
    }

    // Lifecycle methods that should be overridden by subclasses to implement specific behavior.

    /**
     * Called when the component is created.
     * Subclasses should override this method to implement specific setup logic.
     */
    override fun onCreate() {
        // Implement component creation logic in subclasses.
    }

    /**
     * Called when the component is about to become visible.
     * Subclasses should override this method to implement behavior for when the component starts.
     */
    override fun onStart() {
        // Implement component start logic in subclasses.
    }

    /**
     * Called when the component becomes visible.
     * Subclasses should override this method to implement behavior for when the component resumes.
     */
    override fun onResume() {
        // Implement component resume logic in subclasses.
    }

    /**
     * Called when the component is no longer visible.
     * Subclasses should override this method to implement behavior for when the component pauses.
     */
    override fun onPause() {
        // Implement component pause logic in subclasses.
    }

    /**
     * Called when the component is no longer visible and will be destroyed soon.
     * Subclasses should override this method to implement behavior for when the component stops.
     */
    override fun onStop() {
        // Implement component stop logic in subclasses.
    }

    /**
     * Called before the component is destroyed.
     * Subclasses should override this method to implement cleanup logic for the component.
     */
    override fun onDestroy() {
        // Implement component destruction logic in subclasses.
    }

    /**
     * Factory interface for creating and managing components.
     * Implementations of this interface are responsible for creating new components,
     * retrieving a list of components, and releasing resources when no longer needed.
     */
    interface ComponentFactory {
        /**
         * Create new components.
         */
        fun newComponents()

        /**
         * Retrieve the list of components managed by the factory.
         * @return the list of components.
         */
        fun components(): List<Component>

        /**
         * Release any resources or cleanup the components.
         */
        fun release()
    }
}