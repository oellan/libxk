/*
 * Copyright (C) 2019
 *
 * This file is licensed under EUPL v1.2
 */

import org.gradle.api.Project
import org.gradle.kotlin.dsl.NamedDomainObjectContainerScope
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun Project.kotlinMultiplatform(block: KotlinMultiplatformExtension.() -> Unit) {

    apply<KotlinMultiplatformPluginWrapper>()
    (extensions["kotlin"] as KotlinMultiplatformExtension).apply {


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

        block()
    }
}

fun NamedDomainObjectContainerScope<KotlinSourceSet>.commonMain(block: KotlinSourceSet.() -> Unit) =
    getByName("commonMain").apply(block)

fun NamedDomainObjectContainerScope<KotlinSourceSet>.commonTest(block: KotlinSourceSet.() -> Unit) =
    getByName("commonTest").apply(block)
