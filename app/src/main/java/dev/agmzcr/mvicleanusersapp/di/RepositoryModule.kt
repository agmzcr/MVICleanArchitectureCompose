package dev.agmzcr.mvicleanusersapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.agmzcr.mvicleanusersapp.data.repository.UserRepositoryImpl
import dev.agmzcr.mvicleanusersapp.domain.repository.UserRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}