package com.github.stephenwanjala.auth.responses

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val role: String,
    val userName: String,
    val email: String,
    val fullName: String,
    val phoneNumber: String
)
