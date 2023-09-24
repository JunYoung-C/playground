import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea

    val kotlinVersion: String by System.getProperties()
    val springManagementVersion: String by System.getProperties()
    val springBootVersion: String by System.getProperties()

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springManagementVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

apply {
    plugin("kotlin-noarg")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

group = "com.junyoung-c"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-web")

    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
//    implementation("org.springframework.session:spring-session-data-redis")

    // h2
    runtimeOnly("com.h2database:h2:2.1.210")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
