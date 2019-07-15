/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.system.test

import libxk.system.OperatingSystem
import kotlin.test.Test

@Test
fun operatingSystemTest() {

    println("OS family : ${OperatingSystem.family}")
    println("OS version: ${OperatingSystem.version}")
}
