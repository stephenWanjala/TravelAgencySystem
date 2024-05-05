package com.github.stephenwanjala

import com.github.stephenwanjala.auth.data.repositoryImpl.AuthRepositoryImpl
import com.github.stephenwanjala.auth.domain.repository.AuthRepository
import com.github.stephenwanjala.auth.security.hashing.HashingService
import com.github.stephenwanjala.auth.security.hashing.SHA256HashingService
import com.github.stephenwanjala.auth.security.token.JwtTokenService
import com.github.stephenwanjala.auth.security.token.TokenConfig
import com.github.stephenwanjala.plugins.*
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val dbName = "TravelAgency_db"
    val dbPassword = System.getenv("mongoPassword")
    val db =
        KMongo.createClient(connectionString = "mongodb+srv://stephenwanjala145:$dbPassword@database.tr3bttl.mongodb.net/$dbName?retryWrites=true&w=majority").coroutine.getDatabase(
            dbName
        )
    val hashingService: HashingService = SHA256HashingService()
    val authRepository: AuthRepository = AuthRepositoryImpl(db = db)
    val config = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 60 * 60 * 1000L * 24L,
        secrete = System.getenv("JWT_SECRET")
    )
    val tokenService = JwtTokenService()
    configureSecurity(config = config)
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureRouting(
        hashingService = hashingService,
        repository = authRepository,
        tokenService = tokenService,
        config = config
    )
}
