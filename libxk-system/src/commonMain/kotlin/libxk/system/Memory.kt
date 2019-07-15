/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.system

/**
 * Object containing information about memory (RAM).
 */
expect object Memory {

    /**
     * Amount of free virtual memory in bytes.
     */
    val freeVirtualMemory: Long

    /**
     * Total amount of virtual memory in bytes.
     */
    val totalVirtualMemory: Long

    /**
     * Amount of free physical memory in bytes.
     */
    val freePhysicalMemory: Long

    /**
     * Total amount of physical memory in bytes.
     */
    val totalPhysicalMemory: Long
}
