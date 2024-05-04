package com.github.stephenwanjala.auth.requests

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val userName: String,
    val email: String,
    val password: String,
    val phoneNumber : String,
    val fullName:String?=null,
    val role: String
){
    init {
        require(userName.isNotBlank()){"Username must not be blank"}
        require(email.isNotBlank()){"Email must not be blank"}
        require(password.isNotBlank()){"Password must not be blank"}
        require(phoneNumber.isNotBlank()){"Phone number must not be blank"}
        require(validateEmail(email)){"Email is not valid"}
        require(validatePassword(password)){"Password is not strong enough"}
    }

    private fun validateEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
        return emailRegex.matches(email)
    }
//    strong password
    private fun validatePassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$".toRegex()
        return passwordRegex.matches(password)
    }
}

