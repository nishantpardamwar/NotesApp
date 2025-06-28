package com.nishantpardamwar.notesapp.ui

import com.nishantpardamwar.notesapp.ui.model.UINote

sealed interface UIActionNoteHome {
    data object CreateNote : UIActionNoteHome
    data class EditNote(val note: UINote) : UIActionNoteHome
    data class DeleteNote(val note: UINote) : UIActionNoteHome
    data class NoteStateChange(val note: UINote, val isChecked: Boolean) : UIActionNoteHome
    data class NoteDetail(val note: UINote) : UIActionNoteHome
}

sealed interface UIActionNoteCreate {
    data object Back : UIActionNoteCreate
    data class NoteCreate(val title: String, val content: String?) : UIActionNoteCreate
}

sealed interface UIActionNoteEdit {
    data object Back : UIActionNoteEdit
    data class NoteEdit(val note: UINote, val title: String, val content: String?) : UIActionNoteEdit
}

sealed interface UIActionNoteDetail {
    data object Back : UIActionNoteDetail
    data class EditNote(val note: UINote) : UIActionNoteDetail
    data class DeleteNote(val note: UINote) : UIActionNoteDetail
}