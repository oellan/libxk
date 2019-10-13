/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.file

import libxk.io.exceptions.*

/**
 * An abstraction for filesystems.
 */
abstract class FileSystem {

    /**
     * The path elements separator.
     */
    abstract val separator: String

    /**
     * Creates a new empty regular file.
     *
     * @throws UnsupportedOperationException if the filesystem does not support file creation
     * @throws FileAlreadyExistsException if the file already exists
     * @throws IOException if an I/O error occurred
     */
    open fun createRegularFile(file: File): Unit =
        throw UnsupportedOperationException("Not supported by filesystem ${this::class.qualifiedName}")

    /**
     * Creates a new directory. If [createParents] is true, parent directories are created if they do not exist.
     *
     * @throws UnsupportedOperationException if the filesystem does not support directory creation
     * @throws FileAlreadyExistsException if the directory already exists
     * @throws NotDirectoryException when [createParents] is true and an intermediate element in path exists but is not
     * a directory
     * @throws IOException if an I/O error occurred
     */
    open fun createDirectory(
        file: File,
        createParents: Boolean = false
    ): Unit =
        throw UnsupportedOperationException("Not supported by filesystem ${this::class.qualifiedName}")

    /**
     * Deletes a file. If the [file] is a directory. If [file] is a directory and is not empty, the implementation
     * might throw a [DirectoryNotEmptyException].
     *
     * @throws UnsupportedOperationException if the filesystem does not support file deletion.
     * @throws DirectoryNotEmptyException if the file is a not empty directory. This exception is thrown at the
     * discretion of the implementation
     * @throws NoSuchFileException if the file does not exists
     * @throws IOException if an I/O error occurred
     */
    open fun delete(file: File): Unit =
        throw UnsupportedOperationException("Not supported by filesystem ${this::class.qualifiedName}")

    /**
     * Check for the existence of the file.
     *
     * @throws UnsupportedOperationException if the file existence detection isn't supported by the filesystem
     * @throws IOException if an I/O error occurred
     */
    open fun exists(file: File): Boolean =
        throw UnsupportedOperationException("Not supported by filesystem ${this::class.qualifiedName}")

    /**
     * Check if the [file] is a directory.
     *
     * @throws UnsupportedOperationException if the file type detection isn't supported by the filesystem
     * @throws NoSuchFileException if file does not exist
     * @throws IOException if an I/O error occurred
     */
    open fun isDirectory(file: File): Boolean =
        throw UnsupportedOperationException("Not supported by filesystem ${this::class.qualifiedName}")

    /**
     * Check if the [file] is a regular file.
     *
     * @throws UnsupportedOperationException if the file type detection isn't supported by the filesystem
     * @throws NoSuchFileException if file does not exist
     * @throws IOException if an I/O error occurred
     */
    open fun isRegularFile(file: File): Boolean =
        throw UnsupportedOperationException("Not supported by filesystem ${this::class.qualifiedName}")

    /**
     * Read some attributes form the file such as size and file creation, access and modification time.
     *
     * @throws UnsupportedOperationException if the filesystem does not support file attributes
     * @throws NoSuchFileException if file does not exist
     * @throws IOException if an I/O error occurred
     */
    open fun getAttributes(file: File): FileAttributes =
        throw UnsupportedOperationException("Not supported by filesystem ${this::class.qualifiedName}")

    /**
     * Check the path elements and assemble them in a comprehensible way for the filesystem
     *
     * @throws IOException if an I/O error occurred
     */
    abstract fun normalizePath(
        pathElements: Array<String>
    ): String
}
