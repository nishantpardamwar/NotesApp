package com.nishantpardamwar.notesapp.ui.state

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.nishantpardamwar.notesapp.ui.model.UINote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class NotesHomeState(
    val isLoading: Boolean = false, val notesFlow: Flow<PagingData<UINote>> = emptyFlow()
)