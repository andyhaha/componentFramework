package com.andy.modularization

/**
 * The ComponentManager is responsible for managing the collection of components.
 * It allows for adding, removing, and retrieving components that are part of the system.
 * This interface extends ActivityLifeCycle to ensure lifecycle management of components.
 */
interface Container : ActivityLifeCycle {

    /**
     * Retrieves the list of components currently managed by the container.
     * @return a list of all components added to the container
     */
    fun components(): List<Component>

    /**
     * Adds a component to the container.
     * @param component the component to be added
     */
    fun addComponent(component: Component)

    /**
     * Removes a component from the container.
     * @param component the component to be removed
     */
    fun removeComponent(component: Component)
}