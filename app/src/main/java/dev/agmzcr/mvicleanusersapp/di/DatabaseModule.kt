package dev.agmzcr.mvicleanusersapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.agmzcr.mvicleanusersapp.data.local.UserDao
import dev.agmzcr.mvicleanusersapp.data.local.UsersDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): UsersDatabase {
        return Room.databaseBuilder(context, UsersDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: UsersDatabase): UserDao = db.userDao()
}
