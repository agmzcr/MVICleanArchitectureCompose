package dev.agmzcr.mvicleanusersapp.domain.usecase

import androidx.lifecycle.LiveData
import dev.agmzcr.mvicleanusersapp.domain.model.User
import dev.agmzcr.mvicleanusersapp.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): LiveData<List<User>> {
        return repository.getAllUsers()
    }
}
