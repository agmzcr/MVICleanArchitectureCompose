package dev.agmzcr.mvicleanusersapp.data.repository

import androidx.lifecycle.LiveData
import dev.agmzcr.mvicleanusersapp.data.local.UserDao
import dev.agmzcr.mvicleanusersapp.data.remote.UsersApi
import dev.agmzcr.mvicleanusersapp.domain.model.User
import dev.agmzcr.mvicleanusersapp.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UsersApi,
    private val dao: UserDao
) : UserRepository {
    override suspend fun addUser(): User? {
        return try {
            val name = api.getUserName().results[0].name!!
            val location = api.getUserLocation().results[0].location!!
            val picture = api.getUserPicture().results[0].picture!!
            dao.insert(User(name.first, name.last, location.city, picture.thumbnail))
            User(name.first, name.last, location.city, picture.thumbnail)
        } catch (e: IOException) {
            null
        }
    }

    override fun getAllUsers(): LiveData<List<User>> = dao.getAll()

    override suspend fun deleteUser(user: User) = dao.delete(user)
}
