package com.github.stephenwanjala.auth.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
){
    init {
        require(email.isNotBlank()){"Email must not be blank"}
        require(password.isNotBlank()){"Password must not be blank"}
        require(validateEmail(email)){"Email is not valid"}
    }

}

private fun validateEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
    return emailRegex.matches(email)
}
