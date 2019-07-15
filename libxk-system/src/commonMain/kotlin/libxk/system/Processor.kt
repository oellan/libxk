/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.system

/**
 * Object containing information about the installed processor(s) on the system.
 */
expect object Processor {

    val brandName: String

    /**
     * The number of physical core.
     */
    val physicalCoreCount: Int

    /**
     * The number of logical core.
     */
    val logicalCoreCount: Int

    /**
     * The architecture of the processor.
     */
    val architecture: Architecture

    /**
     * An enum describing a processor family.
     */
    enum class Architecture {

        /**
         * Generic 32bit processor.
         */
        X86,

        /**
         * Generic 64bit processor.
         */
        AMD64,

        /**
         * Unknown architecture.
         */
        UNKNOWN
    }
}
