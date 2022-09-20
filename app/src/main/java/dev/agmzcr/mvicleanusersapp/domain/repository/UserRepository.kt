package dev.agmzcr.mvicleanusersapp.domain.repository

import androidx.lifecycle.LiveData
import dev.agmzcr.mvicleanusersapp.domain.model.User

interface UserRepository {
    suspend fun addUser(): User
    fun getAllUsers(): LiveData<List<User>>
    suspend fun deleteUser(user: User)
}
