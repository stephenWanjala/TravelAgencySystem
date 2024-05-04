package com.github.stephenwanjala

import com.github.stephenwanjala.plugins.*
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val dbName ="TravelAgency_db"
    val dbPassword =System.getenv("mongoPassword")
    val db =
        KMongo.createClient(connectionString = "mongodb+srv://stephenwanjala145:$dbPassword@database.tr3bttl.mongodb.net/$dbName?retryWrites=true&w=majority")
            .coroutine
            .getDatabase(dbName)
    configureSecurity()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureRouting()
}
