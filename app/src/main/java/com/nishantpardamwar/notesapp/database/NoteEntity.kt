package com.nishantpardamwar.notesapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val parentNoteId: Long? = null,
    val title: String,
    val content: String?,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)