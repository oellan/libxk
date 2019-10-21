package libxk.io.exceptions

import libxk.io.file.File

/**
 * Signals that an operation for a regular file has been called on another type of file.
 */
@ExperimentalUnsignedTypes
class NotRegularFileException internal constructor(file: String) : IOException("$file is not a regular file") {

    /**
     * Constructs a `NotRegularFileException` with the specified [file].
     *
     * @param file
     * The file
     */
    constructor(file: File) : this(file.path)
}
