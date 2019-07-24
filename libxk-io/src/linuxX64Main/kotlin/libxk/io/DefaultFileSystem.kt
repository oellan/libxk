/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

package libxk.io

import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import libxk.io.exceptions.FileAlreadyExistsException
import libxk.io.exceptions.IOException
import libxk.io.exceptions.NoSuchFileException
import libxk.io.exceptions.NotDirectoryException
import platform.posix.*

@ExperimentalUnsignedTypes
internal actual object DefaultFileSystem : FileSystem() {

    override val separator: String = "/"

    override fun createRegularFile(file: File) {

        if (exists(file))
            throw FileAlreadyExistsException(file)
        val nativeFile = fopen(
            file.path,
            "a"
        )
                         ?: throwIOException()
        if (fclose(nativeFile) == EOF) throwIOException()
    }

    override fun createDirectory(
        file: File,
        createParents: Boolean
    ) {

        fun makeDir(path: String) {

            if (mkdir(
                    path,
                    (S_IRWXU or S_IRGRP or S_IXGRP or S_IROTH or S_IXOTH).toUInt()
                ) != 0
            ) throwIOException()
        }

        if (createParents) {

            var path = ""
            for (segment in (file.path.split(separator).drop(1))) memScoped {

                path += "/$segment"
                val segmentStat = alloc<stat>()
                if (stat(
                        path,
                        segmentStat.ptr
                    ) != 0
                ) {

                    if (errno == ENOENT) makeDir(path)
                    else throwIOException()
                } else {

                    if ((segmentStat.st_mode and S_IFDIR.toUInt()) == S_IFDIR.toUInt()) {

                        makeDir(path)
                    } else {

                        throw NotDirectoryException(path)
                    }
                }
            }
        }
        if (exists(file)) throw FileAlreadyExistsException(file)
        makeDir(file.path)
    }

    override fun delete(file: File) {

        if (!exists(file)) throw NoSuchFileException(file)
        if (remove(file.path) != 0)
            throwIOException()
    }

    override fun exists(file: File) =
        access(
            file.path,
            F_OK
        ) == 0


    override fun isDirectory(file: File) =
        checkFileType(
            file,
            S_IFDIR.toUInt()
        )

    override fun isRegularFile(file: File) =
        checkFileType(
            file,
            S_IFREG.toUInt()
        )

    private fun checkFileType(
        file: File,
        mode: UInt
    ): Boolean = memScoped {

        if (!exists(file)) throw  NoSuchFileException(file)
        val pathStat = alloc<stat>()
        if (stat(
                file.path,
                pathStat.ptr
            ) != 0
        ) throwIOException()
        return@memScoped pathStat.st_mode and mode == mode
    }

    override fun getAttributes(file: File): FileAttributes = memScoped {

        val fileStat = alloc<stat>()
        if (stat(
                file.path,
                fileStat.ptr
            ) != 0
        ) throwIOException()
        return@memScoped FileAttributes(
            lastModifiedTime = fileStat.st_mtim.toMillis(),
            lastAccessTime = fileStat.st_atim.toMillis(),
            size = if (isDirectory(file)) -1 else fileStat.st_size
        )
    }

    override fun normalizePath(pathElements: Array<String>): String {

        if (pathElements.isEmpty()) throw IllegalArgumentException("No path elements provided")
        val isAbsolute = pathElements[0].startsWith('/')
        val elements = mutableListOf<String>()

        pathElements.forEach { pathElement ->

            if ('\u0000' in pathElement) throw IllegalArgumentException("Can't normalize path with null character")
            for (pathSubElement in pathElement.split("[/\\\\]".toRegex())) {

                if (pathSubElement.isEmpty() || pathElement == ".") continue
                if (pathSubElement == "..") {

                    if (elements.size >= 1)
                        elements.removeAt(elements.size - 1)
                } else {

                    elements += pathSubElement
                }
            }
        }

        return elements.joinToString(
            separator,
            prefix = if (isAbsolute) separator else "${`glibc:get_current_dir_name`()}$separator"
        )
    }

    // TODO replace with exception with class
    // TODO globalize this function
    private fun throwIOException(): Nothing = throw IOException(strerror(errno)?.toKString())

    /**
     * This function is copied from https://code.woboq.org/userspace/glibc/io/getdirname.c.html#get_current_dir_name
     *
     * This function is licensed under LGPL 2.1 or later
     */
    @Suppress("FunctionName")
    private fun `glibc:get_current_dir_name`(): String = memScoped {

        val pwd = getenv("PWD")?.toKString()
        val dotStat = alloc<stat>()
        val pwdStat = alloc<stat>()

        return@memScoped if (pwd != null && stat(
                ".",
                dotStat.ptr
            ) == 0
                             && stat(
                pwd,
                pwdStat.ptr
            ) == 0
                             && pwdStat.st_dev == dotStat.st_dev
                             && pwdStat.st_ino == dotStat.st_ino
        ) pwd
        else getcwd(
            null,
            0.toULong()
        )?.toKString()
             ?: throwIOException()
    }

    private fun timespec.toMillis(): Long {

        return this.tv_sec * 1000 + this.tv_nsec / 1000000
    }
}
