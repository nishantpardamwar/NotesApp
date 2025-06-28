package com.nishantpardamwar.notesapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.nishantpardamwar.notesapp.database.NoteEntity
import com.nishantpardamwar.notesapp.repo.Repo
import com.nishantpardamwar.notesapp.ui.model.UINote
import com.nishantpardamwar.notesapp.ui.model.toNoteEntity
import com.nishantpardamwar.notesapp.ui.model.toUINote
import com.nishantpardamwar.notesapp.ui.state.NotesHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMNotesHome @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    private val _state = MutableStateFlow(NotesHomeState())
    val state = _state.asStateFlow()

    init {
        val pagerFlow = repo.notesPager().flow.map {
            it.map { entity -> entity.toUINote() }
        }
        _state.update {
            it.copy(notesFlow = pagerFlow)
        }
    }

    fun updateNoteIsComplete(note: UINote, isComplete: Boolean) {
        viewModelScope.launch {
            val updatedEntity = note.toNoteEntity().copy(isCompleted = isComplete)
            repo.saveNote(updatedEntity)
        }
    }

    fun deleteNote(note: UINote) {
        viewModelScope.launch {
            repo.deleteNote(note.toNoteEntity())
        }
    }
}