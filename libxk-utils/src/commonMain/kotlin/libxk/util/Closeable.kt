package libxk.util

/**
 * An object that can be closed.
 */
interface Closeable {

    /**
     * Closes this object and release resources locked by it.
     *
     * @throws AlreadyClosedException if called when already closed
     */
    fun close()
}
