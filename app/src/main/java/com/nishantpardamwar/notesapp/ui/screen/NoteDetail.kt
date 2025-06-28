package com.nishantpardamwar.notesapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nishantpardamwar.notesapp.ui.UIActionNoteDetail
import com.nishantpardamwar.notesapp.ui.model.UINote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetail(note: UINote, uiAction: (UIActionNoteDetail) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(modifier = Modifier.padding(start = 16.dp), text = "Note Details")
        }, navigationIcon = {
            Icon(
                modifier = Modifier
                    .clickable(onClick = {
                        uiAction(UIActionNoteDetail.Back)
                    })
                    .padding(8.dp), imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
            )
        }, actions = {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = {
                        uiAction(UIActionNoteDetail.DeleteNote(note))
                    }), imageVector = Icons.Filled.Delete, contentDescription = "Delete Note"
            )

            Spacer(Modifier.width(16.dp))

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = {
                        uiAction(UIActionNoteDetail.EditNote(note))
                    }), imageVector = Icons.Filled.Edit, contentDescription = "Edit Note"
            )

            Spacer(Modifier.width(16.dp))
        })
    }, content = { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(text = "Title", fontSize = 12.sp)
                    Text(text = note.title, fontSize = 16.sp)
                }
            }

            Spacer(Modifier.height(8.dp))

            if (!note.content.isNullOrBlank()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Text(text = "Content", fontSize = 12.sp)
                        Text(text = note.content, fontSize = 16.sp)
                    }
                }

                Spacer(Modifier.height(8.dp))
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(text = "State", fontSize = 12.sp)
                    Text(text = if (note.isCompleted) "Complete" else "Pending", fontSize = 16.sp)
                }
            }

            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(text = "Created Date", fontSize = 12.sp)
                    Text(text = note.displayCreatedAt, fontSize = 16.sp)
                }
            }

            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(text = "Last Updated", fontSize = 12.sp)
                    Text(text = note.displayUpdatedAt, fontSize = 16.sp)
                }
            }
        }
    })
}