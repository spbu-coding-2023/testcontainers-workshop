import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    alias(libs.plugins.jvm)
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

configurations {
    all {
        // logging framework configuration — slf4j with log4j2
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}


dependencies {
    // core logic dependencies
    implementation("org.springframework.boot:spring-boot-starter-mail")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // database dependencies
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")

    // logging framework dependencies — slf4j with log4j2
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    // testing framework dependencies
    testImplementation(kotlin("test-junit5"))
    testImplementation(libs.junit.jupiter.engine)
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // testcontainers dependencies
    // use testcontainers to run specific environment in docker container right from tests
    testImplementation("org.testcontainers:testcontainers:1.19.7")
    testImplementation("org.testcontainers:junit-jupiter:1.19.7")
    testImplementation("org.testcontainers:postgresql:1.19.7")

    // rest dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
}
