import java.io.FileFilter

rootProject.name = "libxk"

file(".").listFiles(FileFilter {

    it.isDirectory
    && it.name.startsWith("libxk-")
})!!
    .map(File::name)
    .forEach(::include)
