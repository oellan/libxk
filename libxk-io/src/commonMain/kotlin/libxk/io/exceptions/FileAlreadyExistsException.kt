/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.exceptions

import libxk.io.file.File

/**
 * Signals that an operation using a non-existent file was called on a file that exists.
 */
@ExperimentalUnsignedTypes
class FileAlreadyExistsException internal constructor(file: String) : IOException("$file already exists") {

    /**
     * Constructs a `DirectoryNotEmpty` with the specified [file].
     *
     * @param file
     * The directory
     */
    constructor(file: File) : this(file.path)
}
