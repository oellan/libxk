/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.file

/**
 * An immutable representation of a file or directory in a filesystem.
 *
 * If no [fileSystem] is provided, the [default filesystem][DefaultFileSystem] is used.
 */
@ExperimentalUnsignedTypes
class File : Comparable<File> {

    private val fileSystem: FileSystem

    /**
     * Create a new [File] instance in a [FileSystem] using a [pathName] and [other path elements][others].
     * At creation, the path is normalized using the [FileSystem.normalizePath] method.
     *
     * @param pathName The base
     * @param others Other path elements.
     * @param fileSystem The underlying [FileSystem]. If none is specified, the default one is used.
     */
    constructor(
        pathName: String,
        vararg others: String,
        fileSystem: FileSystem = DefaultFileSystem
    ) {
        this.fileSystem = fileSystem
        this.path = fileSystem.normalizePath(
            arrayOf(pathName) + others
        )
    }

    /**
     * Create a new [File] instance in a [FileSystem] using a [parent] and [other path elements][others].
     * At creation, the path is normalized using the `[FileSystem.normalizePath]` method.
     *
     * @param parent A [File] used as parent.
     * @param others
     * @param fileSystem
     */
    constructor(
        parent: File,
        vararg others: String,
        fileSystem: FileSystem = DefaultFileSystem
    ) : this(
        pathName = parent.path,
        others = *others,
        fileSystem = fileSystem
    )

    internal val path: String

    /**
     * Create a directory denoted by this object. If [createParents] is true, create missing parent directories.
     *
     * @param createParents If true, create missing parent directories.
     */
    fun createDirectory(
        createParents: Boolean = false
    ) = fileSystem.createDirectory(
        this,
        createParents
    )

    /**
     * Creates a new empty file denoted by this object.
     *
     * @return `true` if the file is created. `false` otherwise.
     */
    fun createRegularFile() =
        fileSystem.createRegularFile(this)

    /**
     *
     */
    fun delete() = fileSystem.delete(this)

    /**
     *
     */
    fun exists() = fileSystem.exists(this)

    /**
     *
     */
    fun isDirectory(): Boolean = fileSystem.isDirectory(this)

    /**
     *
     */
    fun isRegularFile(): Boolean = fileSystem.isRegularFile(this)

    /**
     *
     */
    val attributes: FileAttributes
        get() = fileSystem.getAttributes(this)

    /**
     *
     */
    fun hasPermission(
        permission: FilePermission
    ): Boolean =
        fileSystem.hasPermission(
            this,
            permission
        )

    /**
     *
     */
    fun openInputStream() =
        fileSystem.openInputStream(this)

    override fun compareTo(other: File) =
        path.compareTo(other.path)

    override fun toString() = path
}

