package libxk.util

/**
 * An exception thrown when an already closed [Closeable]'s [close method][Closeable.close] is invoked again.
 */
class AlreadyClosedException : Exception {

    constructor() : super()
    constructor(message: String) : super(message)
    constructor(
        message: String,
        cause: Throwable?
    ) : super(
        message,
        cause
    )

    constructor(cause: Throwable) : super(cause)
}
