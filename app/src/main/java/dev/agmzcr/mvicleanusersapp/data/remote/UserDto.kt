package dev.agmzcr.mvicleanusersapp.data.remote

import dev.agmzcr.mvicleanusersapp.domain.model.UserLocation
import dev.agmzcr.mvicleanusersapp.domain.model.UserName
import dev.agmzcr.mvicleanusersapp.domain.model.UserPicture

data class UserDto(
    val name: UserName?,
    val location: UserLocation?,
    val picture: UserPicture?,
)
