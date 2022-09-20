package dev.agmzcr.mvicleanusersapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "lastName") val lastName: String? = null,
    @ColumnInfo(name = "city") val city: String? = null,
    @ColumnInfo(name = "thumbnail") val thumbnail: String? = null,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
