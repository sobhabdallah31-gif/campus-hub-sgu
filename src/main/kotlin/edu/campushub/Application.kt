package edu.campushub

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondRedirect
import io.ktor.server.response.respondText
import io.ktor.server.routing.*

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    val loader = object {}.javaClass
    val pages = mapOf(
        "index.html" to "index.html",
        "student" to "index.html",
        "student.html" to "index.html",
        "instructor" to "instructor.html",
        "instructor.html" to "instructor.html",
        "admin" to "admin.html",
        "admin.html" to "admin.html",
    ).mapValues { (_, resource) ->
        loader.getResource("/$resource")?.readText(Charsets.UTF_8)
            ?: error("Campus Hub page resource /$resource is missing")
    }

    embeddedServer(Netty, port = port, host = "0.0.0.0") {
        routing {
            get("/") { call.respondRedirect("/student") }
            get("/{page}") {
                val page = call.parameters["page"]
                val content = pages[page]
                if (content == null) call.respond(HttpStatusCode.NotFound)
                else call.respondText(content, ContentType.Text.Html)
            }
        }
    }.start(wait = true)
}
