/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.file

import libxk.io.InputStream
import libxk.io.exceptions.*

/**
 * An abstraction for filesystems.
 */
@ExperimentalUnsignedTypes
abstract class FileSystem {

    /**
     * The path elements separator.
     */
    abstract val separator: String

    /**
     * Creates a new empty regular file.
     *
     * @throws UnsupportedByFileSystemException if the filesystem does not support file creation
     * @throws FileAlreadyExistsException if the file already exists
     * @throws IOException if an I/O error occurred
     */
    open fun createRegularFile(file: File): Unit =
        throw UnsupportedByFileSystemException(this::class)

    /**
     * Creates a new directory. If [createParents] is true, parent directories are created if they do not exist.
     *
     * @throws UnsupportedByFileSystemException if the filesystem does not support directory creation
     * @throws FileAlreadyExistsException if the directory already exists
     * @throws NotDirectoryException when [createParents] is true and an intermediate element in path exists but is not
     * a directory
     * @throws IOException if an I/O error occurred
     */
    open fun createDirectory(
        file: File,
        createParents: Boolean = false
    ): Unit =
        throw UnsupportedByFileSystemException(this::class)

    /**
     * Deletes a file. If the [file] is a directory. If [file] is a directory and is not empty, the implementation
     * might throw a [DirectoryNotEmptyException].
     *
     * @throws UnsupportedByFileSystemException if the filesystem does not support file deletion.
     * @throws DirectoryNotEmptyException if the file is a not empty directory. This exception is thrown at the
     * discretion of the implementation
     * @throws NoSuchFileException if the file does not exists
     * @throws IOException if an I/O error occurred
     */
    open fun delete(file: File): Unit =
        throw UnsupportedByFileSystemException(this::class)

    /**
     * Check for the existence of the file.
     *
     * @throws UnsupportedByFileSystemException if the file existence detection isn't supported by the filesystem
     * @throws IOException if an I/O error occurred
     */
    open fun exists(file: File): Boolean =
        throw UnsupportedByFileSystemException(this::class)

    /**
     * Check if the [file] is a directory.
     *
     * @throws UnsupportedByFileSystemException if the file type detection isn't supported by the filesystem
     * @throws NoSuchFileException if file does not exist
     * @throws IOException if an I/O error occurred
     */
    open fun isDirectory(file: File): Boolean =
        throw UnsupportedByFileSystemException(this::class)

    /**
     * Check if the [file] is a regular file.
     *
     * @throws UnsupportedByFileSystemException if the file type detection isn't supported by the filesystem
     * @throws NoSuchFileException if file does not exist
     * @throws IOException if an I/O error occurred
     */
    open fun isRegularFile(file: File): Boolean =
        throw UnsupportedByFileSystemException(this::class)

    /**
     * Read some attributes form the file such as size and file creation, access and modification time.
     *
     * @throws UnsupportedByFileSystemException if the filesystem does not support file attributes
     * @throws NoSuchFileException if file does not exist
     * @throws IOException if an I/O error occurred
     */
    open fun getAttributes(file: File): FileAttributes =
        throw UnsupportedByFileSystemException(this::class)

    /**
     *Check if the program has the [permission] on the [file].
     */
    open fun hasPermission(
        file: File,
        permission: FilePermission
    ): Boolean =
        false

    /**
     * Check the path elements and assemble them in a comprehensible way for the filesystem
     *
     * @throws IOException if an I/O error occurred
     */
    abstract fun normalizePath(
        pathElements: Array<String>
    ): String

    /**
     * Open a stream from a file.
     *
     * @throws UnsupportedByFileSystemException if input streams aren't supported by the filesystem
     */
    open fun openInputStream(
        file: File
    ): InputStream =
        throw UnsupportedByFileSystemException(this::class)
}
