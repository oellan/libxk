/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.system

import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import platform.posix.uname
import platform.posix.utsname

/**
 * Object containing information about the operating system.
 */
actual object OperatingSystem {

    /**
     * The OS' family.
     */
    actual val family = Family.LINUX

    /**
     * The OS version.
     */
    actual val version by lazy {

        memScoped {

            val info = alloc<utsname>()
            if (uname(info.ptr) != 0) throwException()
            info.release.toKString()
        }
    }

    /**
     * An enum describing an operating system family.
     */
    actual enum class Family {

        WINDOWS,
        LINUX,
        UNKNOWN
    }
}
