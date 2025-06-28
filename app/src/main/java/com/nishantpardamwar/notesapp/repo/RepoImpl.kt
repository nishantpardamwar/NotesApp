package com.nishantpardamwar.notesapp.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.nishantpardamwar.notesapp.database.AppDatabase
import com.nishantpardamwar.notesapp.database.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RepoImpl @Inject constructor(
    private val db: AppDatabase
) : Repo {
    override suspend fun saveNote(noteEntity: NoteEntity) {
        db.notesDao().insertNote(noteEntity)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        db.notesDao().deleteNote(noteEntity)
    }

    override fun notesPager(): Pager<Int, NoteEntity> {
        return Pager(
            config = PagingConfig(30), pagingSourceFactory = {
                db.notesDao().getNotesPagingSource()
            })
    }

    override fun observeNoteBy(id: Long): Flow<NoteEntity> {
        return db.notesDao().observeNoteBy(id)
    }
}