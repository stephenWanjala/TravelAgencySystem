package com.github.stephenwanjala.auth.responses

import com.github.stephenwanjala.auth.domain.model.UserRole
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val role: UserRole,
    val userName: String,
    val email: String,
    val fullName: String?=null,
    val phoneNumber: String
)
