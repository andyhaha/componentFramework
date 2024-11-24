package com.andy.modularization

import android.util.Log

/**
 * Manages and provides component communication services for various holders (e.g., Activities, Fragments).
 *
 * This object is responsible for registering, unregistering, retrieving,
 * and releasing service instances associated with specific holders.
 * It ensures isolation between holders and prevents resource leaks
 * by managing services within the scope of each holder.
 */
object ServiceManager {
    // Stores services for each holder. 
    // The outer map's key is the holder ID, and the inner map stores services keyed by their class names.
    val holderServices = mutableMapOf<String, MutableMap<String, Service>>()

    /**
     * Registers a new service for a specific holder.
     *
     * @param holderId A unique identifier for the holder (e.g., Activity, Fragment).
     * @param service The service instance to register.
     */
    @JvmStatic
    fun registerService(holderId: String, service: Service) {
        // Get or create the service map for the holder
        val services = holderServices.getOrPut(holderId) { mutableMapOf() }
        // Register the service using its class name as the key
        services[service.javaClass.name] = service
        Log.d("ServiceManager", "registerService() service " +
                "for holder: $holderId, service.name: ${service.javaClass.name}")
    }

    /**
     * Registers a new service for a specific holder.
     *
     * @param holderId A unique identifier for the holder (e.g., Activity, Fragment).
     * @param service The service instance to register.
     */
//    @JvmStatic
//    inline fun <reified T : Service> registerService(holderId: String, service: T) {
//        // Get or create the service map for the holder
//        val services = holderServices.getOrPut(holderId) { mutableMapOf() }
//        // Register the service using its class name as the key
////        services[T::class.java.name] = service
//        services[service.javaClass.name] = service
//        Log.d("ServiceManager", "registerService() service " +
//                "for holder: $holderId, service.name: ${T::class.java.name}")
//    }

    /**
     * Unregisters a service associated with a specific holder.
     *
     * @param holderId A unique identifier for the holder.
     * @param service The service instance to unregister.
     * @return The unregistered service instance, or null if it was not found.
     */
    @JvmStatic
    fun unregisterService(holderId: String, service: Service): Service? {
        // Remove the service from the holder's service map
        return holderServices[holderId]?.remove(service.javaClass.name)
    }

    /**
     * Retrieves a registered service for a specific holder.
     *
     * @param holderId A unique identifier for the holder.
     * @param key The class name of the service (used as the key during registration).
     * @return The corresponding service instance, or null if it does not exist.
     */
    @JvmStatic
    fun getService(holderId: String, key: String): Service? {
        // Return the service instance associated with the holder ID and key
        return holderServices[holderId]?.get(key)
    }

    /**
     * Retrieves the service instance associated with the specified `holderId`.
     *
     * This method expects the caller to pass the concrete implementation class of the service,
     * not the service interface. For example, if you want to retrieve an instance of `TitleBar`,
     * you should call `getService<TitleBar>(holderId)` rather than `getService<TitleBarService>(holderId)`.
     *
     * @param holderId A unique identifier for the holder.
     * @return The service instance associated with the holder, or null if not found.
     */
    @JvmStatic
    inline fun <reified T : Service> getService(holderId: String): T? {
        Log.d("ServiceManager", "getService() for holder: " +
                "$holderId, service.name: " + T::class.java.name)
        return holderServices[holderId]?.get(T::class.java.name) as? T
    }

    /**
     * Releases all services registered for a specific holder.
     *
     * @param holderId A unique identifier for the holder.
     */
    @JvmStatic
    fun releaseHolderServices(holderId: String) {
        // Remove and clear all services associated with the holder
        holderServices.remove(holderId)?.clear()
    }

    /**
     * Releases all registered services across all holders.
     * Use this with caution, as it affects all managed services.
     */
    @JvmStatic
    fun releaseAllServices() {
        // Clear all services for all holders
        holderServices.clear()
    }
}