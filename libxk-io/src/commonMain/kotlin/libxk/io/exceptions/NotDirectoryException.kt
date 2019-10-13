/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.exceptions

import libxk.io.file.File

/**
 * Signals that an operation for a directory was called on something different than a directory.
 */
class NotDirectoryException internal constructor(directory: String) : IOException("$directory is not a directory") {

    /**
     * Constructs a `NotDirectoryException` with the specified [directory].
     *
     * @param directory
     * The directory
     */
    constructor(directory: File) : this(directory.path)
}
