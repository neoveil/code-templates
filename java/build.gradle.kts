import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
  java
  id("org.springframework.boot") version "3.5.7"
  id("io.spring.dependency-management") version "1.1.7"
}

group = "io.neoveil"
version = "0.1.0"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(25)
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-actuator")

  compileOnly("org.projectlombok:lombok")
  implementation("org.mapstruct:mapstruct:1.6.3")
  implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.8.14")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.projectreactor:reactor-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")

  annotationProcessor("org.projectlombok:lombok")
  annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<BootRun> {
  systemProperty("spring.output.ansi.enabled", "always")
}

tasks.withType<BootJar> {
  destinationDirectory.set(layout.buildDirectory)
  archiveFileName.set("${project.name}-${project.version}.jar")
}
