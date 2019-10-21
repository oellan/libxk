package libxk.io.file

import kotlinx.cinterop.CPointer
import libxk.io.InputStream
import libxk.util.AlreadyClosedException
import platform.posix.EOF
import platform.posix.FILE
import platform.posix.fclose
import platform.posix.fgetc

@ExperimentalUnsignedTypes
class DefaultFileInputStream internal constructor(private val nativeFile: CPointer<FILE>) : InputStream {

    private var isClosed = false

    override fun read(): UByte? {

        val c = fgetc(nativeFile)
        return if (c == EOF) null
        else c.toUByte()
    }

    override fun close() {

        if (isClosed)
            throw AlreadyClosedException()
        if (fclose(nativeFile) == EOF)
            throw RuntimeException("Failed to close file handle")
        isClosed = true
    }
}
