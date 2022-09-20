package dev.agmzcr.mvicleanusersapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.agmzcr.mvicleanusersapp.data.local.UserDao
import dev.agmzcr.mvicleanusersapp.domain.model.User

class UserDaoTest : UserDao {

    private val user1 = User("Daniel", "Gomez", "Madrid", "https://randomuser.me/api/portraits/thumb/men/75.jpg")
    private val user2 = User("Alvaro", "Torres", "Malaga", "https://randomuser.me/api/portraits/thumb/men/75.jpg")

    private val users = MutableLiveData(listOf(user1, user2))

    override fun insert(user: User) {
        users.value = users.value?.toMutableList()?.apply { add(user) }
    }

    override fun getAll(): LiveData<List<User>> = users

    override fun delete(user: User) {
        users.value = users.value?.toMutableList()?.apply { remove(user) }
    }
}
