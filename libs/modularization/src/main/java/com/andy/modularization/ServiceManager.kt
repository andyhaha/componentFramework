package com.andy.modularization

import androidx.collection.arrayMapOf

/**
 * Manages and provides component communication services.
 *
 * This object is responsible for registering, unregistering, and retrieving service instances, ensuring that
 * services can be accessed and used across the application by their service name.
 * It is useful for scenarios where data exchange or method calls need to happen between components or modules.
 */
object ServiceManager {
    // A collection to store services, using an array map for efficient lookup and management.
    private val services = arrayMapOf<String, Service>()

    /**
     * Registers a new service.
     *
     * @param service The service instance to register.
     */
    @JvmStatic
    fun registerService(service: Service) {
        // Using the service's class name as the key and the service instance as the value
        services[service.javaClass.name] = service
    }

    /**
     * Unregisters a previously registered service.
     *
     * @param service The service instance to unregister.
     * @return The unregistered service instance, or null if the service does not exist.
     */
    @JvmStatic
    fun unregisterService(service: Service): Service? {
        // Removes the service based on its class name
        return services.remove(service.javaClass.name)
    }

    /**
     * Retrieves a registered service instance.
     *
     * @param key The name of the service class (the key used during registration).
     * @return The corresponding service instance, or null if the service does not exist.
     */
    @JvmStatic
    fun getService(key: String): Service? {
        // Returns the service corresponding to the given key
        return services[key]
    }

    /**
     * Clears all registered services.
     */
    @JvmStatic
    fun release() {
        // Clears all services from the manager
        services.clear()
    }
}