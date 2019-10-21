package libxk.io.exceptions

import libxk.io.file.FileSystem
import kotlin.reflect.KClass

/**
 * Signals that an operation isn't supported by the filesystem.
 */
@ExperimentalUnsignedTypes
class UnsupportedByFileSystemException(fsClass: KClass<out FileSystem>) : UnsupportedOperationException("Not supported by filesystem ${fsClass.qualifiedName}")
