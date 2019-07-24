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

actual object OperatingSystem {

    actual val family = Family.LINUX

    actual val version by lazy {

        memScoped {

            val info = alloc<utsname>()
            if (uname(info.ptr) != 0) throwException()
            info.release.toKString()
        }
    }

    actual enum class Family {

        WINDOWS,
        LINUX,
        UNKNOWN
    }
}
