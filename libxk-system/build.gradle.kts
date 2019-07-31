kotlinMultiplatform {

    linuxX64 {

        compilations["main"].apply {

            cinterops.create("libcpuid")
        }
    }
}
