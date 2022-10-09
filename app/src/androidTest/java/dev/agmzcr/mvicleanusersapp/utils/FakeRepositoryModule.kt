package dev.agmzcr.mvicleanusersapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.agmzcr.mvicleanusersapp.di.RepositoryModule
import dev.agmzcr.mvicleanusersapp.domain.model.User
import dev.agmzcr.mvicleanusersapp.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun userRepository(): UserRepository =
        object : UserRepository {

            private val users = MutableLiveData<List<User>>(listOf())

            override suspend fun addUser(): User {
                val userList = users.value!!
                val newUser = User(
                    "Name ${userList.size}",
                    "LastName ${userList.size}",
                    "City",
                    "Image",
                )
                users.postValue(users.value?.toMutableList()?.apply { add(newUser) })
                return newUser
            }

            override fun getAllUsers(): LiveData<List<User>> = users

            override suspend fun deleteUser(user: User) {
                users.postValue(users.value?.toMutableList()?.apply { remove(user) })
            }

        }

}