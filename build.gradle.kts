import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper

group = "dev.oellan.libxk"
version = "1"

buildscript {

    dependencies {

        classpath(
            kotlin(
                "gradle-plugin",
                version = "1.3.40"
            )
        )
    }

    repositories {

        jcenter()
    }
}

subprojects {

    apply<KotlinMultiplatformPluginWrapper>()

    kotlinMultiplatform {

        // Linux
        linuxX64()

        sourceSets {

            commonMain {

                dependencies {

                    implementation(kotlin("stdlib-common"))
                }
            }

            commonTest {

                dependencies {

                    implementation(kotlin("test-common"))
                    implementation(kotlin("test-annotations-common"))
                }
            }
        }
    }

    repositories {

        jcenter()
    }
}
