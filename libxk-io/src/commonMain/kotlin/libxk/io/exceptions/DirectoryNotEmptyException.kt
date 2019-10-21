/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.exceptions

import libxk.io.file.File

/**
 * Signals that an operation for an empty directory was called on a non-empty one.
 */
@ExperimentalUnsignedTypes
class DirectoryNotEmptyException internal constructor(directory: String) : IOException("$directory is not empty") {

    /**
     * Constructs a `DirectoryNotEmpty` with the specified [directory].
     *
     * @param directory
     * The directory
     */
    constructor(directory: File) : this(directory.path)
}
