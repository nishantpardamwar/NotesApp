package com.nishantpardamwar.notesapp.di

import com.nishantpardamwar.notesapp.repo.Repo
import com.nishantpardamwar.notesapp.repo.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Singleton
    @Binds
    fun repo(impl: RepoImpl): Repo
}