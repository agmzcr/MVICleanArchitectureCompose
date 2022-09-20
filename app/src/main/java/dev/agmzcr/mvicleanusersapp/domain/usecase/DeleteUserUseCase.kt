package dev.agmzcr.mvicleanusersapp.domain.usecase

import dev.agmzcr.mvicleanusersapp.domain.model.User
import dev.agmzcr.mvicleanusersapp.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(toDelete: User) {
        repository.deleteUser(toDelete)
    }
}
