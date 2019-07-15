/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.system

/**
 * Object containing information about the operating system.
 */
expect object OperatingSystem {

    /**
     * The OS family.
     */
    val family: Family

    /**
     * The OS version.
     */
    val version: String

    /**
     * An enum describing an operating system family.
     */
    enum class Family {

        WINDOWS,
        LINUX,
        UNKNOWN
    }
}
