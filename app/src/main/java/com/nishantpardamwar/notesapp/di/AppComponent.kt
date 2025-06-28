package com.nishantpardamwar.notesapp.di

import android.content.Context
import androidx.room.Room
import com.nishantpardamwar.notesapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "appdb"
    ).build()
}