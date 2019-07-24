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
import native.libcpuid.cpu_id_t
import native.libcpuid.cpu_identify
import native.libcpuid.cpuid_error
import native.libcpuid.cpuid_present
import platform.posix.uname
import platform.posix.utsname

actual object Processor {

    private val cpuid: cpu_id_t = {

        if (cpuid_present() != 1)
            throw RuntimeException("CPUID not supported")

        memScoped {

            val cpuid = alloc<cpu_id_t>()
            if (cpu_identify(
                    null,
                    cpuid.ptr
                ) == 0
            ) throw RuntimeException(
                cpuid_error()?.toKString()
                ?: "Unknown error"
            )
            cpuid
        }
    }()

    actual val brandName: String by lazy {

        return@lazy memScoped {

            val cpuid = alloc<cpu_id_t>()

            cpuid.brand_str.toKString()
        }
    }

    actual val physicalCoreCount by lazy {

        cpuid.num_cores
    }

    actual val logicalCoreCount by lazy {

        cpuid.num_logical_cpus
    }

    actual val architecture: Architecture by lazy {

        memScoped {

            val name = alloc<utsname>()
            if (uname(name.ptr) != 0) throwException()
            parseArch(name.machine.toKString())
        }
    }

    private fun parseArch(arch: String) =
        when {
            arch.contains(
                "amd64",
                true
            ) ||
            arch.contains(
                "x86_64",
                true
            )    -> Architecture.AMD64
            arch.contains(
                "x86",
                true
            )    -> Architecture.X86
            else -> Architecture.UNKNOWN
        }

    actual enum class Architecture {

        X86,

        AMD64,

        UNKNOWN;
    }
}
