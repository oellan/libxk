package libxk.io.file

import platform.posix.R_OK
import platform.posix.W_OK
import platform.posix.X_OK

actual enum class FilePermission(val accessMode: Int) {

    READ(R_OK),
    WRITE(W_OK),
    EXECUTE(X_OK)
}
