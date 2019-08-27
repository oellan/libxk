plugins {

    `kotlin-dsl`
}

repositories {

    jcenter()
}

dependencies {

    implementation(
        kotlin(
            "gradle-plugin",
            version = "1.3.50"
        )
    )
}
