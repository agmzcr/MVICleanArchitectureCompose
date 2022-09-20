package dev.agmzcr.mvicleanusersapp.domain.usecase

import dev.agmzcr.mvicleanusersapp.domain.model.User
import dev.agmzcr.mvicleanusersapp.domain.repository.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(): User {
        return repository.addUser()
    }
}
