/*
 * Copyright (c) 2019
 *
 * License under EUPL v1.2
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12
 */

group = "dev.oellan.libxk"
version = "3"

buildscript {

    dependencies {

        classpath(
            kotlin(
                "gradle-plugin",
                version = "1.3.50"
            )
        )
    }

    repositories {

        jcenter()
    }
}

allprojects {

    repositories {

        jcenter()
    }
}
