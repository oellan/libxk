/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.system.test

import libxk.system.Memory
import kotlin.test.Test

@Test
fun memoryTest() {

    println("Detected ${Memory.freePhysicalMemory} bytes of free physical memory")
    println("Detected ${Memory.totalPhysicalMemory} bytes of physical memory")
    println("Detected ${Memory.freeVirtualMemory} bytes of free virtual memory")
    println("Detected ${Memory.totalVirtualMemory} bytes of virtual memory")
}
