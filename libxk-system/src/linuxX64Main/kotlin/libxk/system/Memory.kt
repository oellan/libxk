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

/**
 * Object containing information about memory (RAM).
 */
@ExperimentalUnsignedTypes
actual object Memory {

    /**
     * Amount of free virtual memory in bytes.
     */
    actual val freeVirtualMemory: Long
        get() = memScoped {

            val info = alloc<sysinfo>()
            if (sysinfo(info.ptr) != 0) throwException()
            ((info.freeram + info.freeswap) * info.mem_unit).toLong()
        }

    /**
     * Total amount of virtual memory in bytes.
     */
    actual val totalVirtualMemory by lazy {

        memScoped {

            val info = alloc<sysinfo>()
            if (sysinfo(info.ptr) != 0) throwException()
            ((info.totalram + info.totalswap) * info.mem_unit).toLong()
        }
    }

    /**
     * Amount of free physical memory in bytes.
     */
    actual val freePhysicalMemory: Long
        get() = memScoped {

            val info = alloc<sysinfo>()
            if (sysinfo(info.ptr) != 0) throwException()
            (info.freeram * info.mem_unit).toLong()
        }

    /**
     * Total amount of physical memory in bytes.
     */
    actual val totalPhysicalMemory by lazy {

        memScoped {

            val info = alloc<sysinfo>()
            if (sysinfo(info.ptr) != 0) throwException()
            (info.totalram * info.mem_unit).toLong()
        }
    }
}
