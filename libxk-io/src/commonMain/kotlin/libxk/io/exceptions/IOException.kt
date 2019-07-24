/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.exceptions

/**
 * Signals that an I/O exception of some sort has occurred.
 */
open class IOException : Exception {

    /**
     * Constructs an `IOException` with the specified message and cause. Either [message] and [cause] are nullable.
     *
     * @param message
     * The detail message
     *
     * @param cause
     * The cause
     */
    @Suppress("ConvertSecondaryConstructorToPrimary")
    constructor(
        message: String? = null,
        cause: Throwable? = null
    ) : super(
        message,
        cause
    )
}
