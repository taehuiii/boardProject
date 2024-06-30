import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.23"
//    kotlin("kapt") version "1.8.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17 //추가
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get()) //추가
    }
}

repositories {
    mavenCentral()
}

val kotestVersion = "5.5.5" // 추가 for test
val mockkVersion = "1.13.8" // 추가! for test


//추가
noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}
//추가
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    implementation("com.h2database:h2")
    //runtimeOnly("com.h2database:h2")
    runtimeOnly("com.h2database:h2:1.4.200")
    //추가
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

    //추가 for test
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion") // 추가 !!
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion") // 추가 !!
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3") // 추가 !!
    testImplementation("io.mockk:mockk:$mockkVersion") // 추가 !!
    testImplementation("org.postgresql:postgresql")

    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")

    //추가 For Querydsl
//    implementation("com.querydsl:querydsl-jpa:5.0.0")
//    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test>().configureEach { // 변경 !!
    useJUnitPlatform()
}

//추가 for QueryDSL
//kapt {
//    arguments {
//        arg("querydsl.entityAccessors", "true")
//        arg("querydsl.entityAccessors", "true")
//    }
//}
//
//sourceSets {
//    main {
//        java {
//            srcDir("build/generated/source/kapt/main")
//        }
//    }
//}


// DB ㅇ연결
//https://velog.io/@yangwon-park/Kotlin-Querydsl-%EC%84%B8%ED%8C%85
//querydsl 재설정