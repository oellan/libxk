/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.system

import kotlinx.cinterop.toKString
import platform.posix.errno
import platform.posix.strerror

fun throwException(): Nothing =
    throw RuntimeException(
        strerror(errno)?.toKString()
        ?: "Unknown error"
    )
