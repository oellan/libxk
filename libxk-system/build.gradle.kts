kotlinMultiplatform {

    linuxX64 {

        compilations["main"].apply {

            cinterops.create("libcpuidLinuxX64")
        }
    }
}