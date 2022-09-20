package dev.agmzcr.mvicleanusersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.agmzcr.mvicleanusersapp.domain.model.User

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
