/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.system.test

import libxk.system.Processor
import kotlin.test.Test

@Test
fun processorTest() {

    println("Processor brand name     : " + Processor.brandName)
    println("Processor architecture   : " + Processor.architecture)
    println("Processor logical cores  : " + Processor.logicalCoreCount)
    println("Processor physical cores : " + Processor.physicalCoreCount)
}
