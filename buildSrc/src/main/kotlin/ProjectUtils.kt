/*
 * Copyright (C) 2019
 *
 * This file is licensed under EUPL v1.2
 */

import org.gradle.api.Project
import org.gradle.kotlin.dsl.NamedDomainObjectContainerScope
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun Project.kotlinMultiplatform(block: KotlinMultiplatformExtension.() -> Unit) {

    try {

        (extensions["kotlin"] as KotlinMultiplatformExtension)
    } catch (_: Throwable) { // Kotlin plugin not applied. Do not execute "block"

        return
    }.block()
}

fun NamedDomainObjectContainerScope<KotlinSourceSet>.commonMain(block: KotlinSourceSet.() -> Unit) =
    getByName("commonMain").apply(block)

fun NamedDomainObjectContainerScope<KotlinSourceSet>.commonTest(block: KotlinSourceSet.() -> Unit) =
    getByName("commonTest").apply(block)
