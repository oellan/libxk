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
import platform.linux.sysinfo

@ExperimentalUnsignedTypes
actual object Memory {

    actual val freeVirtualMemory: Long
        get() = memScoped {

            val info = alloc<sysinfo>()
            if (sysinfo(info.ptr) != 0) throwException()
            ((info.freeram + info.freeswap) * info.mem_unit).toLong()
        }

    actual val totalVirtualMemory by lazy {

        memScoped {

            val info = alloc<sysinfo>()
            if (sysinfo(info.ptr) != 0) throwException()
            ((info.totalram + info.totalswap) * info.mem_unit).toLong()
        }
    }

    actual val freePhysicalMemory: Long
        get() = memScoped {

            val info = alloc<sysinfo>()
            if (sysinfo(info.ptr) != 0) throwException()
            (info.freeram * info.mem_unit).toLong()
        }

    actual val totalPhysicalMemory by lazy {

        memScoped {

            val info = alloc<sysinfo>()
            if (sysinfo(info.ptr) != 0) throwException()
            (info.totalram * info.mem_unit).toLong()
        }
    }
}
