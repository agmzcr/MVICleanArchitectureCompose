package dev.agmzcr.mvicleanusersapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.agmzcr.mvicleanusersapp.domain.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAll(): LiveData<List<User>>

    @Delete
    fun delete(user: User)
}
