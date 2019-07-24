/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io

/**
 * A class to store various file attributes.
 */
expect class FileAttributes {

    /**
     * The creation time in millis. The precision is dependent on the underlying filesystem. If not available, `-1`
     * should be returned.
     */
    val creationTime: Long

    /**
     * The last modification time in millis. The precision is dependent on the underlying filesystem. If not available,
     * `-1`should be returned.
     */
    val lastModifiedTime: Long

    /**
     * The last access time in millis. The precision is dependent on the underlying filesystem. If not available, `-1`
     * should be returned.
     */
    val lastAccessTime: Long

    /**
     * The size of the file in bytes. Must be `-1` if file is a directory.
     */
    val size: Long
}
