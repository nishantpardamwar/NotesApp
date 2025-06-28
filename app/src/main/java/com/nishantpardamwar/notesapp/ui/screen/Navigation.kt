package com.nishantpardamwar.notesapp.ui.screen

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.nishantpardamwar.notesapp.ui.ScreenRoutes
import com.nishantpardamwar.notesapp.ui.UIActionNoteCreate
import com.nishantpardamwar.notesapp.ui.UIActionNoteDetail
import com.nishantpardamwar.notesapp.ui.UIActionNoteEdit
import com.nishantpardamwar.notesapp.ui.UIActionNoteHome
import com.nishantpardamwar.notesapp.vm.VMNoteCreate
import com.nishantpardamwar.notesapp.vm.VMNoteDetail
import com.nishantpardamwar.notesapp.vm.VMNoteEdit
import com.nishantpardamwar.notesapp.vm.VMNotesHome

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun Navigation(modifier: Modifier) {
    val backStack = rememberNavBackStack<ScreenRoutes>(ScreenRoutes.NotesHome)
    val listDetailStrategy = rememberListDetailSceneStrategy<Any>()
    NavDisplay(
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ), transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(targetOffsetX = { -it })
        }, popTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(targetOffsetX = { it })
        }, onBack = { backStack.removeLastOrNull() }, backStack = backStack,
        sceneStrategy = listDetailStrategy,
        entryProvider = entryProvider {
            entry<ScreenRoutes.NotesHome>(metadata = ListDetailSceneStrategy.listPane()) { screen ->
                val vm = hiltViewModel<VMNotesHome>()
                val state by vm.state.collectAsStateWithLifecycle()
                NotesHome(state = state, uiAction = { action ->
                    when (action) {
                        is UIActionNoteHome.CreateNote -> backStack.add(ScreenRoutes.NoteCreate)
                        is UIActionNoteHome.NoteStateChange -> vm.updateNoteIsComplete(action.note, action.isChecked)
                        is UIActionNoteHome.EditNote -> backStack.add(ScreenRoutes.NoteEdit(action.note))
                        is UIActionNoteHome.DeleteNote -> vm.deleteNote(action.note)
                        is UIActionNoteHome.NoteDetail -> backStack.add(ScreenRoutes.NoteDetail(action.note))
                    }
                })
            }

            entry<ScreenRoutes.NoteCreate> { screen ->
                val vm = hiltViewModel<VMNoteCreate>()
                NoteCreate(uiAction = { action ->
                    when (action) {
                        is UIActionNoteCreate.Back -> backStack.removeLastOrNull()
                        is UIActionNoteCreate.NoteCreate -> {
                            vm.saveNote(action.title, action.content, null)
                            backStack.removeLastOrNull()
                        }
                    }
                })
            }

            entry<ScreenRoutes.NoteEdit> { screen ->
                val vm = hiltViewModel<VMNoteEdit>()
                NoteEdit(screen.note, uiAction = { action ->
                    when (action) {
                        is UIActionNoteEdit.Back -> backStack.removeLastOrNull()
                        is UIActionNoteEdit.NoteEdit -> {
                            vm.saveNote(action.note, action.title, action.content)
                            backStack.removeLastOrNull()
                        }
                    }
                })
            }

            entry<ScreenRoutes.NoteDetail>(metadata = ListDetailSceneStrategy.detailPane()) { screen ->
                val vm = hiltViewModel<VMNoteDetail, VMNoteDetail.Factory>(creationCallback = { factory ->
                    factory.create(screen.note.id)
                })
                val note by vm.noteFlow.collectAsStateWithLifecycle(initialValue = screen.note)
                NoteDetail(note, uiAction = { action ->
                    when (action) {
                        UIActionNoteDetail.Back -> backStack.removeLastOrNull()
                        is UIActionNoteDetail.DeleteNote -> {
                            backStack.removeLastOrNull()
                            vm.deleteNote(action.note)
                        }

                        is UIActionNoteDetail.EditNote -> backStack.add(ScreenRoutes.NoteEdit(action.note))
                    }
                })
            }
        })
}