package com.nishantpardamwar.notesapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishantpardamwar.notesapp.database.NoteEntity
import com.nishantpardamwar.notesapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMNoteCreate @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    fun saveNote(title: String, content: String?, parentNoteId: Long?) {
        viewModelScope.launch {
            val entity = NoteEntity(
                parentNoteId = parentNoteId,
                title = title,
                content = content,
                isCompleted = false,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            repo.saveNote(entity)
        }
    }
}