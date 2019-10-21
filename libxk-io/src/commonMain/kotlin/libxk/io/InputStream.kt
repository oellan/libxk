package libxk.io

import libxk.util.Closeable

/**
 * This interface represents an input stream of bytes.
 */
@ExperimentalUnsignedTypes
interface InputStream : Closeable {

    /**
     * Reads the next byte from the stream.
     *
     * @return the next byte as a [UByte] or `null` if there is no more byte to read.
     */
    fun read(): UByte?
}
