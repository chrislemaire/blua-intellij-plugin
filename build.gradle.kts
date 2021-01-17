import org.jetbrains.intellij.tasks.BuildSearchableOptionsTask
import org.jetbrains.intellij.tasks.PatchPluginXmlTask

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java

    id("org.jetbrains.intellij") version "0.6.5"
    kotlin("jvm") version "1.4.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2020.3.1"
    isDownloadSources = true
    pluginName = "blua"

    setPlugins("PsiViewer:203-SNAPSHOT", "izhangzhihao.rainbow.brackets:6.13")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

val patchPluginXml by tasks.getting(PatchPluginXmlTask::class) {
    changeNotes("""
      Add change notes here.<br>
      <em>most HTML tags may be used</em>""")
}

val buildSearchableOptions by tasks.getting(BuildSearchableOptionsTask::class) {
    enabled = false
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=enable")
    }
}

sourceSets {
    main {
        java {
            srcDirs(file("src/main/java"), file("src/main/gen"))
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit", "junit", "4.12")
}
