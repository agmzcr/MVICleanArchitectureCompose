package dev.agmzcr.mvicleanusersapp.data.remote

import retrofit2.http.GET

interface UsersApi {

    @GET("?inc=name")
    suspend fun getUserName(): ApiResponse

    @GET("?inc=location")
    suspend fun getUserLocation(): ApiResponse

    @GET("?inc=picture")
    suspend fun getUserPicture(): ApiResponse
}
