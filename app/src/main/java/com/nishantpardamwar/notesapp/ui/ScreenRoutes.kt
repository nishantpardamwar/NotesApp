package com.nishantpardamwar.notesapp.ui

import androidx.navigation3.runtime.NavKey
import com.nishantpardamwar.notesapp.ui.model.UINote
import kotlinx.serialization.Serializable

@Serializable
sealed interface ScreenRoutes : NavKey {
    @Serializable
    data object NotesHome : ScreenRoutes

    @Serializable
    data object NoteCreate : ScreenRoutes

    @Serializable
    data class NoteEdit(val note: UINote) : ScreenRoutes

    @Serializable
    data class NoteDetail(val note: UINote) : ScreenRoutes
}