package com.github.stephenwanjala.auth.data.repositoryImpl

import com.github.stephenwanjala.auth.domain.model.User
import com.github.stephenwanjala.auth.domain.repository.AuthRepository
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class AuthRepositoryImpl(
    db: CoroutineDatabase
) : AuthRepository {
    private val users = db.getCollection<User>()
    override suspend fun signUp(user: User): Boolean = users.insertOne(user).wasAcknowledged()

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }

    override suspend fun findUserByEmail(email: String): User? = users.findOne(User::email eq email)

    override suspend fun findUserByUserName(userName: String): User? = users.findOne(User::userName eq userName)
}