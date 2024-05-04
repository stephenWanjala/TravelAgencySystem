package com.github.stephenwanjala.plugins

import com.github.stephenwanjala.auth.domain.repository.AuthRepository
import com.github.stephenwanjala.auth.routes.signIn
import com.github.stephenwanjala.auth.routes.signUp
import com.github.stephenwanjala.auth.security.hashing.HashingService
import com.github.stephenwanjala.auth.security.token.JwtTokenService
import com.github.stephenwanjala.auth.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    hashingService: HashingService,
    repository: AuthRepository,
    tokenService: JwtTokenService,
    config: TokenConfig
) {
    routing {
        signUp(hashingService = hashingService, repository = repository)
        signIn(
            hashingService = hashingService,
            repository = repository,
            tokenService = tokenService,
            config = config
        )
    }
}
