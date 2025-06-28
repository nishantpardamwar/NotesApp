package com.nishantpardamwar.notesapp.repo

import androidx.paging.Pager
import com.nishantpardamwar.notesapp.database.NoteEntity
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun saveNote(noteEntity: NoteEntity)
    suspend fun deleteNote(noteEntity: NoteEntity)
    fun notesPager(): Pager<Int, NoteEntity>
    fun observeNoteBy(id: Long): Flow<NoteEntity>
}