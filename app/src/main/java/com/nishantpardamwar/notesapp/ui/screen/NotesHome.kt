package com.nishantpardamwar.notesapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.nishantpardamwar.notesapp.ui.UIActionNoteDetail
import com.nishantpardamwar.notesapp.ui.UIActionNoteHome
import com.nishantpardamwar.notesapp.ui.model.UINote
import com.nishantpardamwar.notesapp.ui.state.NotesHomeState

@Composable
fun NotesHome(state: NotesHomeState, uiAction: (UIActionNoteHome) -> Unit) {
    val notes = state.notesFlow.collectAsLazyPagingItems()
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            uiAction(UIActionNoteHome.CreateNote)
        }) {
            Icon(Icons.Filled.Add, "Create Note")
        }
    }, content = { paddingValues ->
        LazyColumn(
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(notes.itemCount, key = notes.itemKey { it.id }) { index ->
                val note = notes[index] ?: return@items
                NoteItem(Modifier.animateItem(), note, uiAction)
            }
        }
    })
}

@Composable
private fun NoteItem(modifier: Modifier, note: UINote, uiAction: (UIActionNoteHome) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = note.isCompleted, onCheckedChange = {
                uiAction(UIActionNoteHome.NoteStateChange(note, it))
            })

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = {
                        uiAction(UIActionNoteHome.NoteDetail(note))
                    }),
                text = note.title,
                textDecoration = if (note.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickable(onClick = {
                        uiAction(UIActionNoteHome.DeleteNote(note))
                    }), imageVector = Icons.Filled.Delete, contentDescription = "Delete Note"
            )

            Spacer(Modifier.width(16.dp))

            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickable(onClick = {
                        uiAction(UIActionNoteHome.EditNote(note))
                    }), imageVector = Icons.Filled.Edit, contentDescription = "Edit Note"
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}