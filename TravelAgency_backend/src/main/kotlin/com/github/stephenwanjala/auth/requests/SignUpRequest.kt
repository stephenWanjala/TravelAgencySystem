package com.github.stephenwanjala.auth.requests

import com.github.stephenwanjala.auth.domain.model.User
import com.github.stephenwanjala.auth.domain.model.UserRole
import com.github.stephenwanjala.auth.security.hashing.SaltedHash
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val userName: String,
    val email: String,
    val password: String,
    val phoneNumber : String,
    val fullName:String?=null,
    val role: String= UserRole.CUSTOMER.name
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

    fun allFieldsValid():Boolean{
        return userName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && phoneNumber.isNotBlank() && validateEmail(email) && validatePassword(password)
    }

    fun toUser(saltedHash: SaltedHash): User =
        User(
            userName = userName,
            email = email,
            password = saltedHash.hash,
            salt = saltedHash.salt,
            phoneNumber = phoneNumber,
            fullName = fullName,
            role = UserRole.valueOf(role)
        )
}

