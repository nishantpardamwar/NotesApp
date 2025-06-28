package com.nishantpardamwar.notesapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNote(noteEntity: NoteEntity)

    @Delete
    abstract suspend fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM noteentity ORDER BY createdAt DESC")
    abstract fun getNotesPagingSource(): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    abstract fun observeNoteBy(id: Long): Flow<NoteEntity>
}