package com.andy.modularization

import android.app.Activity

/**
 * This class represents a container that simulates an Activity lifecycle.
 * It is responsible for managing components and their lifecycle, mimicking
 * the activity's lifecycle methods.
 */
open class ActivityContainer(val activity: Activity) : Container {
    private val components = arrayListOf<Component>()

    /**
     * Reloads all components in the container by calling their performReload() method.
     * This simulates a refresh of the components, typically when the Activity's state
     * needs to be reset.
     */
    fun reload() {
        components.forEach {
            it.performReload()
        }
    }

    /**
     * Called when the container is first created.
     * This method can be overridden to perform initialization logic.
     */
    override fun onCreate() {
        // Add any logic needed when the container is created (e.g., setup)
    }

    /**
     * Called when the container is about to become visible, similar to an Activity's onStart().
     * This method resumes all components by calling their performResume() method.
     */
    override fun onStart() {
        components.forEach {
            it.performResume()
        }
    }

    /**
     * Called when the container has become visible, similar to an Activity's onResume().
     * This method resumes all components by calling their performResume() method.
     */
    override fun onResume() {
        components.forEach {
            it.performResume()
        }
    }

    /**
     * Called when the container is no longer visible, similar to an Activity's onPause().
     * This method pauses all components by calling their performPause() method.
     */
    override fun onPause() {
        components.forEach {
            it.performPause()
        }
    }

    /**
     * Called when the container is no longer visible and will be destroyed soon,
     * similar to an Activity's onStop().
     * This method stops all components by calling their performStop() method.
     */
    override fun onStop() {
        components.forEach {
            it.performStop()
        }
    }

    /**
     * Returns the list of components currently contained in this container.
     * This simulates the retrieval of components or fragments in an Activity.
     * @return the list of components
     */
    override fun components(): List<Component> {
        return components
    }

    /**
     * Adds a component to the container and calls its performCreate() method to initialize it.
     * This mimics the addition of a Fragment or component to an Activity.
     * @param component the component to be added
     */
    override fun addComponent(component: Component) {
        components.add(component)
        component.performCreate() // Initialize the component when it is added
    }

    /**
     * Removes a component from the container.
     * This simulates the removal of a Fragment or component from an Activity.
     * @param component the component to be removed
     */
    override fun removeComponent(component: Component) {
        components.remove(component)
    }

    /**
     * Called when the container is being destroyed, similar to an Activity's onDestroy().
     * Calls performDestroy() on all components and clears the component list.
     */
    override fun onDestroy() {
        components.forEach {
            it.performDestroy()
        }
        components.clear() // Clean up the components when the container is destroyed
    }
}