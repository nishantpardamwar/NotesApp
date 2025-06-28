package com.nishantpardamwar.notesapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishantpardamwar.notesapp.repo.Repo
import com.nishantpardamwar.notesapp.ui.model.UINote
import com.nishantpardamwar.notesapp.ui.model.toNoteEntity
import com.nishantpardamwar.notesapp.ui.model.toUINote
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = VMNoteDetail.Factory::class)
class VMNoteDetail @AssistedInject constructor(
    @Assisted private val id: Long,
    private val repo: Repo,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(id: Long): VMNoteDetail
    }

    val noteFlow = repo.observeNoteBy(id).map { it.toUINote() }

    fun deleteNote(note: UINote) {
        viewModelScope.launch {
            repo.deleteNote(note.toNoteEntity())
        }
    }
}