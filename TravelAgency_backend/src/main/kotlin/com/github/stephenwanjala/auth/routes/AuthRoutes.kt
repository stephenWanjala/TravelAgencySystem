package com.github.stephenwanjala.auth.routes

import com.github.stephenwanjala.auth.domain.repository.AuthRepository
import com.github.stephenwanjala.auth.requests.LoginRequest
import com.github.stephenwanjala.auth.requests.SignUpRequest
import com.github.stephenwanjala.auth.responses.LoginResponse
import com.github.stephenwanjala.auth.security.hashing.HashingService
import com.github.stephenwanjala.auth.security.hashing.SaltedHash
import com.github.stephenwanjala.auth.security.token.TokenClaim
import com.github.stephenwanjala.auth.security.token.TokenConfig
import com.github.stephenwanjala.auth.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signUp(hashingService: HashingService, repository: AuthRepository) {
    post("/signup") {
        val request = call.receiveNullable<SignUpRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }
        if (!request.allFieldsValid()) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }
        val isUserExist = repository.findUserByEmail(request.email) != null
        if (isUserExist) {
            call.respond(message = "Email Address Taken", status = HttpStatusCode.Conflict)
            return@post
        }
        val isUserNameExist = repository.findUserByUserName(request.userName) != null
        if (isUserNameExist) {
            call.respond(message = "Username Taken", status = HttpStatusCode.Conflict)
            return@post
        }
        val saltedHash = hashingService.generateSaltedHash(value = request.password)
        val user = request.toUser(saltedHash)
        val isUserCreated = repository.signUp(user)
        if (isUserCreated) {
            call.respond(message = "User Created", status = HttpStatusCode.Created)
        } else {
            call.respond(message = "User Not Created", status = HttpStatusCode.InternalServerError)
            return@post
        }

    }
}

fun Route.signIn(
    hashingService: HashingService,
    repository: AuthRepository,
    tokenService: TokenService,
    config: TokenConfig
) {
    post("/signin") {
        val request = call.receiveNullable<LoginRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }
        val userExist = repository.findUserByEmail(request.email) ?: kotlin.run {
            call.respond(message = "User Not Found", status = HttpStatusCode.NotFound)
            return@post
        }
        val saltedHash = SaltedHash(salt = userExist.salt, hash = userExist.password)
        val isValidPassword = hashingService.verify(
            value = request.password, saltedHash = saltedHash
        )

        if (!isValidPassword) {
            call.respond(message = "Bad Credentials", status = HttpStatusCode.Unauthorized)
            return@post
        }
        val token = tokenService.generateToken(
            config = config,
            TokenClaim(
                name = "userId", value = userExist.id.toString()
            ),
            TokenClaim(name = "userName", value = userExist.userName),
            TokenClaim(name = "email", value = userExist.email),
            TokenClaim(name = "role", value = userExist.role.name),

        )
        call.respond(
            HttpStatusCode.OK,
            LoginResponse(
                token = token,
                userName = userExist.userName,
                email = userExist.email,
                role = userExist.role,
                fullName = userExist.fullName,
                phoneNumber = userExist.phoneNumber
            )
        )
    }
}


fun Route.authenticate(){
    authenticate {
        get("/authenticate"){
            val principal = call.principal<UserIdPrincipal>()
            call.respond(HttpStatusCode.OK, "Authenticated")
        }
    }
}