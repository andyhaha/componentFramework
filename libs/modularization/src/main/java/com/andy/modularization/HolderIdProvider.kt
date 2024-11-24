package com.andy.modularization

/**
 * Interface for providing a unique holder identifier (holderId).
 *
 * Classes implementing this interface should define a unique `holderId`,
 * which is typically used to associate and manage services or components
 * related to specific holders (e.g., Activities, Fragments, or other entities).
 *
 * This design helps isolate and manage dependencies or services
 * within the scope of the holder, enabling better resource control and modularity.
 */
interface HolderIdProvider {
    /**
     * A unique identifier for the holder.
     *
     * This ID is used to register, retrieve, and manage services or other entities
     * associated with the holder. It must be unique within the application's context
     * to avoid conflicts between different holders.
     */
    val holderId: String
}