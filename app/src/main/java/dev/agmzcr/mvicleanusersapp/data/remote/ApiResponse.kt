package dev.agmzcr.mvicleanusersapp.data.remote

data class ApiResponse(
    val results: List<UserDto> = emptyList()
)
