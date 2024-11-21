package com.example.viewspractice.model

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers(): List<User> {
        delay(8000)

        return listOf<User>(
            User(0, "faheem"),
            User(1, "pheeem"),
            User(2, "neem"),
            User(3, "beem"),
            User(4, "jeem"),
        )
    }
}