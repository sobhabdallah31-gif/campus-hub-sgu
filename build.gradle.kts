plugins {
    kotlin("jvm") version "2.1.10"
    application
}

group = "edu.campushub"
version = "1.0.0"

repositories { mavenCentral() }

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:3.1.1")
    implementation("io.ktor:ktor-server-netty-jvm:3.1.1")
    implementation("ch.qos.logback:logback-classic:1.5.16")
}

kotlin { jvmToolchain(17) }

application { mainClass.set("edu.campushub.ApplicationKt") }

tasks.processResources {
    from("index.html")
    from("instructor.html")
    from("admin.html")
}
