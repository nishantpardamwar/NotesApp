package com.nishantpardamwar.notesapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishantpardamwar.notesapp.database.NoteEntity
import com.nishantpardamwar.notesapp.repo.Repo
import com.nishantpardamwar.notesapp.ui.model.UINote
import com.nishantpardamwar.notesapp.ui.model.toNoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMNoteEdit @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    fun saveNote(note: UINote, title: String, content: String?) {
        viewModelScope.launch {
            val entity = note.toNoteEntity().copy(
                title = title, content = content, updatedAt = System.currentTimeMillis()
            )
            repo.saveNote(entity)
        }
    }
}