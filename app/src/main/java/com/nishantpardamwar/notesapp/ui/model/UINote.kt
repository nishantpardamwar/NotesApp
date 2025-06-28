package com.nishantpardamwar.notesapp.ui.model

import androidx.compose.runtime.Immutable
import com.nishantpardamwar.notesapp.database.NoteEntity
import com.nishantpardamwar.notesapp.displayDate
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class UINote(
    val id: Long,
    val parentNoteId: Long?,
    val title: String,
    val content: String?,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long
) {
    val displayCreatedAt = createdAt.displayDate()
    val displayUpdatedAt = updatedAt.displayDate()
}

fun UINote.toNoteEntity() = NoteEntity(
    id = this.id,
    parentNoteId = this.parentNoteId,
    title = this.title,
    content = this.content,
    isCompleted = this.isCompleted,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun NoteEntity.toUINote() = UINote(
    id = this.id,
    parentNoteId = this.parentNoteId,
    title = this.title,
    content = this.content,
    isCompleted = this.isCompleted,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)