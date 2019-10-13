/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io.file

actual data class FileAttributes internal constructor(
    actual val lastModifiedTime: Long,
    actual val lastAccessTime: Long,
    actual val size: Long
) {

    actual val creationTime: Long = -1
}
