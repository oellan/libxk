/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.exceptions

import libxk.io.file.File

/**
 * Signals that an operation was called on a non existent file.
 */
class NoSuchFileException internal constructor(file: String) : IOException("$file do not exists") {

    /**
     * Constructs a `NoSuchFileException` with the specified [file].
     *
     * @param file
     * The file
     */
    constructor(file: File) : this(file.path)
}
